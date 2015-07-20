package ninefoo.view.include.menu.dialog;

import ninefoo.config.RoleNames;
import ninefoo.config.Session;
import ninefoo.lib.autocompleteComboBox.AutocompleteComboBox;
import ninefoo.lib.component.PMButton;
import ninefoo.lib.component.PMLabel;
import ninefoo.lib.lang.LanguageText;
import ninefoo.lib.layout.dialog.CenterFormSouthButtonDialog;
import ninefoo.lib.layout.dialog.FormDialog;
import ninefoo.model.object.Member;
import ninefoo.view.include.menu.listener.ToolsListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


/**
 * Dialog that shows lists of users to be added as members or managers
 *
 * @author Sebouh Bardakjian
 */

public class AddUserToProjectDialog extends CenterFormSouthButtonDialog {

    private static final long serialVersionUID = 4530177660846739513L;
    // Define components
    private PMButton addButton;
    private AutocompleteComboBox memberBox;
    private JComboBox<String> roleBox;
    private ArrayList<Member> users;
    private String[] usersName;

    /**
     * Constructor
     */
    public AddUserToProjectDialog(JFrame parentFrame, final ToolsListener toolsListener) {

        // Load user
        toolsListener.loadAllMembersForAddUserToProjectDialog(AddUserToProjectDialog.this);

        // Initialize components
        this.addButton = new PMButton("ADD");
        this.memberBox = new AutocompleteComboBox(usersName);
        this.roleBox = new JComboBox<>(RoleNames.ROLES);

        // Set the title
        this.setTitle(LanguageText.getConstant("ADD_USER_PRO"));

        // Add button listener
        this.addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (toolsListener != null) {
                    if (memberBox.checkAndGetText() == null || memberBox.getSelectedIndex() < 0) {
                        // TODO put this in the language file
                        AddUserToProjectDialog.this.setErrorMessage("Please choose a member!");
                    } else {
                        int memberId = users.get(memberBox.getSelectedIndex()).getMemberId();
                        toolsListener.addUserToProject(AddUserToProjectDialog.this, memberId, Session.getInstance().getProjectId(), roleBox.getSelectedItem().toString());
                    }
                }
            }
        });

        // Add center form
        this.setCenterPanel(new FormDialog() {

            private static final long serialVersionUID = 206877090385767375L;

            @Override
            public void placeForm() {
                // Set border title
                this.titledBorder.setTitle(String.format("%s >> %s", LanguageText.getConstant("USER"), LanguageText.getConstant("PROJECT")));

                // Set input border
                memberBox.setBorder(BorderFactory.createCompoundBorder(memberBox.getBorder(), inputPadding));
                roleBox.setBorder(BorderFactory.createCompoundBorder(roleBox.getBorder(), inputPadding));

                // Add components
                this.table.put(new PMLabel("USER"));
                this.table.put(memberBox);

                this.table.newRow();
                this.table.put(new PMLabel("ROLE"));
                this.table.put(roleBox);
            }
        });

        // Add component to south panel
        this.southPanel.add(addButton);

        // Configure dialog
        this.setSize(new Dimension(300, 350));
        this.setLocationRelativeTo(parentFrame);
        this.setResizable(false);
        this.setVisible(true);
    }

    //TODO Add refresh

    /**
     * Populate list
     *
     * @param users
     */
    public void populateUserList(List<Member> users) {

        // Reset array
        this.users = new ArrayList<>();

        // If a list was returned
        if (users != null) {

            // Add projects
            this.users.addAll(users);
        }

        // Create array
        this.usersName = new String[users.size()];

        // Loop on users
        for (int i = 0; i < users.size(); i++)
            usersName[i] = users.get(i).getUsername();
    }
}

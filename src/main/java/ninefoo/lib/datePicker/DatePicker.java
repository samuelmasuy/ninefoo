package ninefoo.lib.datePicker;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import ninefoo.config.Config;

import javax.swing.JFormattedTextField.AbstractFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * JDatepPciler library
 *
 * @author Amir El Bawab
 */
public class DatePicker extends JDatePickerImpl {

    private static final long serialVersionUID = 1176607692711926273L;
    private UtilDateModel model;

    /**
     * Constructor
     */
    public DatePicker() {
        this(10);
    }

    /**
     * Constructor
     *
     * @param columns
     */
    public DatePicker(int columns) {
        super(new JDatePanelImpl(new UtilDateModel()), new AbstractFormatter() {
            private static final long serialVersionUID = -1445665019970462352L;

            private SimpleDateFormat dateFormatter = new SimpleDateFormat(Config.DATE_FORMAT_SHORT);

            @Override
            public Object stringToValue(String text) throws ParseException {
                return dateFormatter.parseObject(text);
            }

            @Override
            public String valueToString(Object value) throws ParseException {
                if (value != null) {
                    Calendar cal = (Calendar) value;
                    return dateFormatter.format(cal.getTime());
                }

                return "";
            }
        });

        this.model = (UtilDateModel) this.getModel();
        this.getJFormattedTextField().setColumns(columns);
    }

    /**
     * Set today
     */
    public void setToday() {
        Calendar c = Calendar.getInstance();
        this.model.setDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        this.model.setSelected(true);
    }

    /**
     * Set date
     *
     * @param year
     * @param month
     * @param day
     */
    public void setDate(int year, int month, int day) {
        this.model.setDate(year, month, day);
        this.model.setSelected(true);
    }

    /**
     * Set date
     *
     * @param date
     */
    public void setDate(Date date) {

        // If date is not null
        if (date != null) {
            this.model.setValue(date);
            this.model.setSelected(true);
        }
    }

    /**
     * Get text value
     *
     * @return field value
     */
    public String getText() {
        return this.getJFormattedTextField().getText();
    }
}
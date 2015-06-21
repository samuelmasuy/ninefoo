package ninefoo.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import ninefoo.model.object.Member;
import ninefoo.model.sql.Member_model;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

//delete database members before *re-running* all tests
public class Member_modelTest 
{
	@BeforeClass
	static public void dbCreator(){
		DbManager.createTables();
		
	}
	
	@Test
	public void test01AddNewMember() 
		{
		/**
		 * We want to make sure that when a new member is added, the correct information 
		 * has been stored in the database
		 * We are testing to see if the information of a member that has been added
		 * is equivalent to the information that is retrieved from the database after 
		 * the member has been added.
		 *
		 */
		Member aMember= new Member("b", "b", "b", "b");
		Member_model mem_model=new Member_model();
		int idWhenInsertedInDB = mem_model.insertNewMember(aMember);
		Member fetchedMember= mem_model.getMemberByUsername(aMember.getUsername());
		assertEquals(fetchedMember.getUsername(), aMember.getUsername());
		assertEquals(fetchedMember.getFirstName(), aMember.getFirstName());
    	assertEquals(fetchedMember.getLastName(), aMember.getLastName());
		assertEquals(fetchedMember.getMemberId(), idWhenInsertedInDB);
		assertEquals(fetchedMember.getPassword(), aMember.getPassword());
		
		
		}
	
	@Test
	 public void test02GetAllMember(){
		/**
		 * To see whether or not a member has been added to the database,
		 * We check the length of the list of members. If X new members are added, 
		 * the list should be longer of X.
		 */
		
		 Member memA= new Member("c", "c", "c", "c");
		 Member memB= new Member("d", "d", "d", "d");
		 Member memC= new Member("e", "e", "e", "e");
		 Member_model mem_model=new Member_model();//empty
		 int num1=mem_model.getAllMembers().size();
		
		 
		 
		 //we should see the length of the array bigger
		 //because we added new elements 
		 mem_model.insertNewMember(memA);
		 mem_model.insertNewMember(memB);
		 mem_model.insertNewMember(memC);
		 mem_model.getAllMembers();
		 int num2=mem_model.getAllMembers().size(); 	
		
		 assertEquals(num1+3, num2);
		 
	 }
	 
	@Test
	public void test03GetMemberById(){
		
		/**
		 * When adding a new member object to the database, the ID of the member is return as an int.
		 * We are testing to see if the int returned is greater than zero , meaning that the member has been added 
		 * succesfully. When a member could not be added, the returned int is 0.
		 */
		Member mem= new Member("f", "f", "f", "f");
		 Member_model mem_model=new Member_model();
		 int idOfNewlyInsertedMember=mem_model.insertNewMember(mem);
		assertTrue(idOfNewlyInsertedMember > 0);
	}
	
	@Test
	public void test04GetMemberByUsername(){
		
		/**
		 * We are testing to see if retrieving a member from the database returns valid Member information.
		 * To test, we are inserting a new member into the database, and retrieving the same member from 
		 * the database by its username. The information stored in the database should be equal to the information 
		 *  when it is retrieved of the database.
		 */
		Member aMember= new Member("g", "g", "g", "g");
		 Member_model mem_model=new Member_model();
		int idWhenInsertedInDB=mem_model.insertNewMember(aMember);
		 String usernameNewMember=aMember.getUsername();
		 Member fetchedMember=mem_model.getMemberByUsername(usernameNewMember);
		assertEquals(fetchedMember.getUsername(), aMember.getUsername());
		assertEquals(fetchedMember.getFirstName(), aMember.getFirstName());
		assertEquals(fetchedMember.getLastName(), aMember.getLastName());
		assertEquals(fetchedMember.getMemberId(), idWhenInsertedInDB);
		assertEquals(fetchedMember.getPassword(), aMember.getPassword());
			
		
	}
	
	@Test
	public void test05DeleteMember(){
		/**
		 * We are testing to see if, when we call the delete method on a member X, that the member 
		 * is no longer contained in the database.
		 * To test, we are inserting a member in to the database and retaining the retunred ID in an int variable
		 * we are then retrieving the member from the database with its respective ID
		 * And we are deleting the member from the database and updating the member object information
		 * The member should be null, because it no longer exists as a member object or a database member
		 */
		Member memD= new Member("h", "h", "h", "h");
		 Member_model mem_model=new Member_model();
		int memID=mem_model.insertNewMember(memD);
		Member memDB= mem_model.getMemberById(memID);
		mem_model.deleteMember(memDB);
		memDB= mem_model.getMemberById(memID);//update content of a same member ID, by returning a null object
		assertNull(memDB);
	}
	 	
	@Test
	public void test06DeleteMemberById(){
		/**
		 * We are testing to see if, when we call the delete  by ID method on a member X, that the member 
		 * is no longer contained in the database.
		 * To test, we are inserting a member in to the database 
		 * We are then deleting it by its ID in the database
		 * We are also updating the member object to point to an empty member
		 * The member should be null, because it no longer exists as a member object or a database member
		 */
		
		
		Member mem= new Member("i", "i", "i", "i");
		 Member_model mem_model=new Member_model();
		 int memID=mem_model.insertNewMember(mem);
		// Member memDB=mem_model.getMemberById(memID);
		 mem_model.deleteMemberById(memID);
		 Member memDB=mem_model.getMemberById(memID);//get new object returned from db of the deleted object, should therefore be a null object since it has been deleteds
		 assertNull(memDB);
	}
	@Test
	public void test07DeleteMemberByUsername(){
		
		/**
		 * We are testing to see if, when we call the delete  by Username method on a member X, that the member 
		 * is no longer contained in the database.
		 * To test, we are inserting a member in to the database 
		 * We are then deleting it by its Username in the database
		 * We are also updating the member object to point to an empty member
		 * The member should be null, because it no longer exists as a member object or a database member
		 */
		Member mem= new Member("i", "i", "i", "i");
		 Member_model mem_model=new Member_model();
		 mem_model.insertNewMember(mem);
		 mem_model.deleteMemberByUsername("i");
		 Member memDB=mem_model.getMemberByUsername(mem.getUsername());
		 assertNull(memDB);
	}
	 
}

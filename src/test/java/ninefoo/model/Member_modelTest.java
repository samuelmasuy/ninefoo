package ninefoo.model;

import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class Member_modelTest 
{

	
	public void test01AddNewMember() 
		{
		/**
		 * We want to make sure that when a new member is added, the correct information 
		 * has been stored in the database
		 * How: 
		 * >Testing to see if the information of a member that has been added
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
	
	
	 public void test02GetAllMember(){
		/**
		 * To see wheither 
		 */
		
		 System.out.println("hello");
		 Member memA= new Member("c", "c", "c", "c");
		 Member memB= new Member("d", "d", "d", "d");
		 Member memC= new Member("e", "e", "e", "e");
		 Member_model mem_model=new Member_model();//empty
		 int num1=mem_model.getAllMembers().size();
		 System.out.println(num1);
		 
		 
		 //we should see the length of the array bigger
		 //because we added new elements 
		 mem_model.insertNewMember(memA);
		 mem_model.insertNewMember(memB);
		 mem_model.insertNewMember(memC);
		 mem_model.getAllMembers();
		 int num2=mem_model.getAllMembers().size(); 	
		 System.out.println(num2);
		 assertEquals(num1+3, num2);
		 
	 }
	 
	
	public void test03GetMemberById(){
		Member mem= new Member("f", "f", "f", "f");
		 Member_model mem_model=new Member_model();
		 int idOfNewlyInsertedMember=mem_model.insertNewMember(mem);
		assertTrue(idOfNewlyInsertedMember != 0);
	}
	
	public void test04GetMemberByUsername(){
		Member aMember= new Member("f", "f", "f", "f");
		 Member_model mem_model=new Member_model();
		int idWhenInsertedInDB=mem_model.insertNewMember(aMember);
		 String usernameNewMember=aMember.getUsername();
		 Member fetchedMember=mem_model.getMemberByUsername(usernameNewMember);
		assertEquals(fetchedMember.getUsername(), "tralala" /*aMember.getUsername()*/);
		assertEquals(fetchedMember.getFirstName(), aMember.getFirstName());
		assertEquals(fetchedMember.getLastName(), aMember.getLastName());
		assertEquals(fetchedMember.getMemberId(), idWhenInsertedInDB);
		assertEquals(fetchedMember.getPassword(), aMember.getPassword());
			
		
	}
	
	public void testDeleteMember(){
		Member memD= new Member("f", "f", "f", "f");
		 Member_model mem_model=new Member_model();
		int memID=mem_model.insertNewMember(memD);
		System.out.println(memID);
		Member memDB= mem_model.getMemberById(memID);
		mem_model.deleteMember(memDB);
		memDB= mem_model.getMemberById(memID);//update content of a same member ID, by returning a null object
	/*	 assertEquals(null, memDB.getFirstName());
		 assertEquals(null, memDB.getLastName());
		 assertEquals(null, memDB.getPassword());
		 assertEquals(null, memDB.getUsername());
		 assertEquals(null, memDB.getMemberId());*/
	//	assertNull(memDB.getFirstName());
		assertNull(memDB);
	}
	 	
	public void testDeleteMemberById(){
		Member mem= new Member("f", "f", "f", "f");
		 Member_model mem_model=new Member_model();
		 int memID=mem_model.insertNewMember(mem);
		// Member memDB=mem_model.getMemberById(memID);
		 mem_model.deleteMemberById(memID);
		 Member memDB=mem_model.getMemberById(memID);//get new object returned from db of the deleted object, should therefore be a null object since it has been deleteds
		 assertNull(memDB);
	}
	@Test
	public void testDeleteMemberByUsername(){
		Member mem= new Member("f", "f", "mel", "f");
		 Member_model mem_model=new Member_model();
		 mem_model.insertNewMember(mem);
		 //Member memDB=mem_model.getMemberByUsername(mem.getUsername());
	//	String username=mem.getUsername();
		mem_model.deleteMemberByUsername("mel");
		 //Member memDB=mem_model.getMemberByUsername(mem.getUsername());
		// assertNull(memDB);
	}
	 
}

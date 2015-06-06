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
	@Test
	public void testDeleteMember(){
		Member memD= new Member("f", "f", "f", "f");
		 Member_model mem_model=new Member_model();
		int memID=mem_model.insertNewMember(memD);
		System.out.println(memID);
		Member meminDB= mem_model.getMemberById(memID);
		mem_model.deleteMember(meminDB);
		meminDB= mem_model.getMemberById(memID);
		 //assertEquals(null, memD.getFirstName());
		 //assertEquals(null, memD.getLastName());
		 //assertEquals(null, memD.getPassword());
		 //assertEquals(null, memD.getUsername());
		 //assertEquals(null, memD.getMemberId());
		assertNull(meminDB);
	}
	 
	public void testDeleteMemberById(){
		Member memD= new Member("f", "f", "f", "f");
		 Member_model mem_model=new Member_model();
		 int idNewMember=mem_model.insertNewMember(memD);
		
		 mem_model.deleteMemberById(idNewMember);
		 assertEquals(null, memD.getFirstName());
		 assertEquals(null, memD.getLastName());
		 assertEquals(null, memD.getPassword());
		 assertEquals(null, memD.getUsername());
		 assertEquals(null, memD.getMemberId());
		 assertNull(memD);
	}
	
	public void testDeleteMemberByUsername(){
		Member memD= new Member("f", "f", "f", "f");
		 Member_model mem_model=new Member_model();
		 mem_model.insertNewMember(memD);
		 mem_model.deleteMemberByUsername(memD.getUsername());
		 assertEquals(null, memD.getFirstName());
		 assertEquals(null, memD.getLastName());
		 assertEquals(null, memD.getPassword());
		 assertEquals(null, memD.getUsername());
		 assertEquals(null, memD.getMemberId());
		 assertNull(memD);
	}
	 
}

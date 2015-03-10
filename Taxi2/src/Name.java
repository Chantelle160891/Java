

public class Name {
		private String firstName;
		private String middleName;
		private String lastName;

		public Name(String fName, String lName)
		{
			firstName = fName;   
			 middleName = ""; 
			 lastName = lName;    

		}
		
		public Name(String fName, String mName, String lName)
		{
			firstName = fName;   
			 middleName = mName; 
			 lastName = lName;    

		}
		
		public Name(String fullName)
		{
			  int spacePos1 = fullName.indexOf(' ');
			  firstName = fullName.substring(0, spacePos1);
			  int spacePos2 = fullName.lastIndexOf(' ');
			  if (spacePos1 == spacePos2)
				  middleName = "";
			  else 
				  middleName = fullName.substring(spacePos1+1, spacePos2);
			  lastName = fullName.substring(spacePos2 + 1);
		}
		
		public void setFirstName(String fName)
		{
			firstName = fName;
		}
		public String getFirstName() 
		{
			
			return firstName;
		}
		
		public void setMiddleName(String mName)
		{
			middleName = mName;
		}
		public String getMiddleName() 
		{
				return middleName;
		}
		
		public void setLastName(String lName)
		{
			lastName = lName;
		}
		public String getLastName()
		{
			return lastName;
		}
		
		public String getFullName() 
		{
			  String result = firstName + " ";
			  if (!middleName.equals("")) {
				  result += middleName + " ";
			  }
			  result += lastName;
			  return result;	  
		  }
		
		public String GetInitials()
		{
			String result = "";
			result += firstName.charAt(0);
			if(!middleName.equals(""))
			{
				result += middleName.charAt(0);
			}
			result += lastName.charAt(0);
			return result;
		}

}

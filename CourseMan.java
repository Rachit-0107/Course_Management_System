import java.util.*;  

class Test{
    public String testName;
    public float weightagePercent;
    public int duration;
    public int noOfQuestions;
    public ArrayList<Character> answerKey=new ArrayList<>();
    public String testSituation = "Available"; 
    
    public Test(String testName, float weightagePercent , int duration , int noOfQuestions)
    {
    	this.testName = testName; 
    	this.weightagePercent = weightagePercent;
    	this.duration = duration;
    	this.noOfQuestions = noOfQuestions; 
    	
    }
   
    void setKey(ArrayList<Character> ansKey){ 
    	for(int i =0; i<ansKey.size(); i++)
    	{
    		answerKey.add(ansKey.get(i)); 
    	}
    }
    int evaluatingTest(ArrayList<Character> enteredAnskey){ 
    	int marks = 0; 
    	for(int i =0; i<enteredAnskey.size();i++)
    	{
    		if(enteredAnskey.get(i) == answerKey.get(i))
    		{
    			marks += 4; 
    		} 
    		else if(enteredAnskey.get(i) == 'N')
    		{
    			marks += 0; 
    		} 
    		else
    		{
    			marks -= 1; 
    		}
    	}
        return marks;
    }
}
 
class TestDetails{
    public String name;
	public String attemptStatus;
    public float marks;	
    
    TestDetails()
    {
    	attemptStatus = "Unattempted"; 
    	marks = 0; 
    }
 }
class CourseDetails{
	public String nameCourse; 
	public int unitsCourse; 
	public int Classroom; 
	public List<String> Components = new LinkedList<String>(); 
	public List<String> topics = new LinkedList<String>(); 
	public List<String> idNoofStudentsenrolled = new ArrayList<String>(); 
	public List<String> nameofStudents = new ArrayList<String>(); 
	
	CourseDetails(String nameCourse,int unitsCourse,int Classroom)
	{
		this.Classroom = Classroom; 
		this.nameCourse = nameCourse; 
		this.unitsCourse = unitsCourse; 
	}
}
	
class Course extends CourseDetails{
	public int no_of_students;
	public List<Test> allTest = new ArrayList<Test>();  
	
	Course(String nameCourse,int unitsCourse,int Classroom, int no_of_students)
	{
		super(nameCourse,unitsCourse,Classroom); 
		this.no_of_students = no_of_students; 
	}
} 

class Student{
    public String courseName="Object Oriented Programming";
    public String name;
    public String idNumber;
    public String emailAddress;
    public String country;
    public  HashMap<String, Integer> marks = new HashMap<>(); 
    public List<TestDetails> testActivity = new ArrayList<TestDetails>();
    Scanner in = new Scanner(System.in);

        
    Student(String name,String idNumber,String emailAddress,String country){
        this.name=name;
        this.idNumber=idNumber;
        this.emailAddress=emailAddress + "@pilani.bits-pilani.ac.in";
        this.country=country;
    }
    
    int marksOfTest(String testName){
         if(testName=="Quiz")
         {
             return marks.get("Quiz");
         }
         if(testName=="Midsemester Examination")
         {
            return marks.get("Midsemester Examination");
        }
        if(testName=="Comprehensive Examination")
        {
            return marks.get("Comprehensive Examination");
        }
        else
        	return 0;
    }
    public ArrayList<String> availableTests(Course c1){ 
    	Test t; 
    	ArrayList<String> avlTest = new ArrayList<String>();
    	for(int i =0; i<c1.allTest.size(); i++)
    	{
    		t =c1.allTest.get(i); 
    		if(t.testSituation.equals("Available"))
    		{
    			if(this.testActivity.get(i).attemptStatus.equals("Unattempted")); 
    			{
    				avlTest.add(this.testActivity.get(i).name);
    			}
    		} 
    		else
    		{
    			;
    		}
    	}
        return avlTest;
    }
    String viewEnrolledCourse(){
        return courseName;
    }
    void viewPersonalDetails(){
        System.out.println("Name: "+name);
        System.out.println("BITS ID: "+idNumber);
        System.out.println("Email Address: "+emailAddress);
        System.out.println("Country: "+country);
        System.out.println("Quiz: "+marks.get("Quiz")+" "+"Mid semester Examination: "+marks.get("Midsemester Examination")+" "+"Comprehensive Examination: "+marks.get("Comprehensive Examination"));
    }
    void allRegisteredStudents(Course c1){ 
    	for(int i=0; i<c1.nameofStudents.size(); i++)
    	{
    		System.out.println(c1.nameofStudents.get(i)+" "+ c1.idNoofStudentsenrolled.get(i));
    	}
    } 
    public void takeTest(Course c1)
    {
    	ArrayList<String> avltest = this.availableTests(c1); 
    	Test t; 
    	ArrayList<Character> answers = new ArrayList<Character>();
    	int index=0; 
    	for(int i=0; i<avltest.size();i++)
    	{
    		System.out.println(avltest.get(i));
    	}
    	System.out.println("From the above List please enter the Test you wish to take");
    	String attemptTest = in.next();
    	for(int i =0; i<c1.allTest.size();i++)
    	{
    		if(c1.allTest.get(i).testName.equals(attemptTest))
    		{
    			 index = i; 
    		}
    	} 
    	t = c1.allTest.get(index);
    	System.out.println("Please enter the answers for the questions"); 
    	System.out.println("Enter a,b,c,d to answer the question or N is you don't want to attempt");
    	System.out.println("Every quetion carries +4 for correct answer and -1 for wrong answer");
    	
    	for(int i=0; i<t.noOfQuestions;i++)
    	{
    		Character c;
    		c = in.next().charAt(0);
    		answers.add(c);
    	} 
    	
    	int markgot = t.evaluatingTest(answers); 
    	marks.put(attemptTest, markgot);
    	
    }
}


class Teacher{
    String name;
    String email_id;
    ArrayList<String> courses; 
    Teacher(String name, String email_id)
    {
    	this.name = name; 
    	this.email_id = email_id+"@pilani.bits-pilani.ac.in";
    }
 
    void courseDetails(){
        for(int i=0;i<courses.size();i++){
            System.out.println(courses.get(i));
        }
    } 
    
    Map<String , Integer> viewMarks(Student st){
        Map<String, Integer> testMarks = new HashMap<String, Integer>();
        testMarks.put("Quiz",st.marks.get("Quiz"));
        testMarks.put("Midsemester Examination",st.marks.get("Midsemester Examination"));
        testMarks.put("Comprehensive Examination",st.marks.get("Comprehensive Examination"));
        return testMarks;
    }
    void createTest(String testName, float weightagePercent , int duration , int noOfQuestions, Course c1){
        Test t = new Test(testName,weightagePercent ,duration ,noOfQuestions); 
        c1.allTest.add(t);
        System.out.println("New Test Created");
        ArrayList<Character> anskey=new ArrayList<>();
        for(int i=0;i<noOfQuestions;i++){
        anskey.add('a');
        }
        t.setKey(anskey);
    }
}

class CourseMan {

	public static void main(String[] args) { 
		//Scanner sc=new Scanner(System.in);
		Course OOP = new Course("Object Oriented Programming",4,5102,3);
		Teacher AD = new Teacher("Amit Dua", "amit.d12"); 
		Student stud1 = new Student("Harshit","2020A7PS0057P","f20200057","India"); 
		Student stud2 = new Student("Rachit","2020A7PS0033P","f20200033","India");
        OOP.nameofStudents.add(stud1.name);
        OOP.idNoofStudentsenrolled.add(stud1.idNumber);
        OOP.nameofStudents.add(stud2.name);
        OOP.idNoofStudentsenrolled.add(stud2.idNumber);
		AD.createTest("Quiz",30.0f, 1, 10, OOP);
        AD.createTest("Midsemester Examination",30.0f, 2, 12, OOP);
        AD.createTest("Comprehensive Examination",40.0f, 3, 15, OOP);
        stud1.viewPersonalDetails();
        stud2.viewPersonalDetails();
        stud1.allRegisteredStudents(OOP);
        System.out.println(stud1.availableTests(OOP));
        Map<String,Integer> mp =new HashMap<>();
        mp=AD.viewMarks(stud2);
        System.out.println(mp);
	}

}

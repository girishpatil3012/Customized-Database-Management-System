import java.lang.*;
import java.util.*;

class Student
{
    public int RID;
    public String Name;
    public int Salary;

    private static int Generator = 0;

    static 
    {
        Generator = 0;
    }

    public Student(String str, int value)
    {
        this.RID = ++Generator;
        this.Name = str;
        this.Salary = value;
    }

    public void DisplayData()
    {
        System.out.println(this.RID+"\t"+ this.Name+ "\t"+ this.Salary);
    }
}

class DBMS
{
    public LinkedList <Student> lobj; 

    public DBMS()
    {
        lobj = new LinkedList<>();
    }

    // Insert into student Girish 1000
    // select * from student
    public void StartDBMS()
    {
        Scanner scanobj = new Scanner(System.in);

        System.out.println("Marvellous customised DBMS started successfully...");
        String Query = "";

        while(true)
        {
            System.out.print("Marvellous DBMS Console > ");
            Query = scanobj.nextLine();
            Query = Query.toLowerCase();

            String tokens[] = Query.split(" ");
            int QuerySize = tokens.length;

            if(QuerySize == 1)
            {
                if("help".equals(tokens[0]))
                {
                    System.out.println("--------------------------------------------------------------------------------------------");
                    System.out.println("This application is used to demonstrates the customized DBMS");
                    System.out.println("--------------------------------------------------------------------------------------------");
                    System.out.println("exit : Terminate DBMS");
                    System.out.println("Display all data : select * from student");
                    System.out.println("Insert data : insert into student Name Salary");
                    System.out.println("Display data with Name : select name Name from student");
                    System.out.println("Display data with RID : select rid RID from student");
                    System.out.println("Delete data by RID : delete from student where rid = RID");
                    System.out.println("Delete data by Name : delete from student where name = Name");
                    System.out.println("Display minimum salary : select min salary from student");
                    System.out.println("Display maximum salary : select max salary from student");
                    System.out.println("Display sum of salaries : select sum salary from student");
                    System.out.println("Display average salary : select avg salary from student");
                    System.out.println("Display count of total records : select count from student");
                    System.out.println("Update salary using RID : update salary to Salary where rid = RID");
                    System.out.println("Update name using RID : update name to Name where rid = RID");
                    System.out.println("Display salary greater range records : select * from student where salary > Salary");
                    System.out.println("Display salary small range records : select * from student where salary < Salary");
                    System.out.println("Display RID greater range records : select * from student where rid > RID");
                    System.out.println("Display RID smaller range records : select * from student where rid < RID");
                    System.out.println("--------------------------------------------------------------------------------------------");
                    System.out.println("--------------------------------------------------------------------------------------------");
                }
                else if("exit".equals(tokens[0]))
                {
                    System.out.println("Thank you for using Marvellous DBMS");
                    break;
                }
            }
            else if(QuerySize == 2)
            {

            }
            else if(QuerySize == 4)
            {
                //select * from student
                //select count from student
                if("select".equals(tokens[0]))
                {
                    if("*".equals(tokens[1]))
                    {
                        DisplayAll();
                    }
                    else if("count".equals(tokens[1]))
                    {
                        AggregateCount();
                    }
                }
            }
            else if(QuerySize == 5)
            {
                // Insert into student Girish 1000
                if("insert".equals(tokens[0]))
                {
                    InsertData(tokens[3],Integer.parseInt(tokens[4]));
                }

                //select name Name from student
                //select rid RID from student
                //select min salary from student
                //select max salary from student
                //select sum salary from student
                //select avg salary from student
                if("select".equals(tokens[0]))
                {
                    if("name".equals(tokens[1]))
                    {
                        DisplaySpecific(tokens[2]);
                    }
                    else if("rid".equals(tokens[1]))
                    {
                        DisplaySpecific(Integer.parseInt(tokens[2]));
                    }
                    else if("min".equals(tokens[1]))
                    {
                        AggregateMin();
                    }
                    else if("max".equals(tokens[1]))
                    {
                        AggregateMax();
                    }
                    else if("sum".equals(tokens[1]))
                    {
                        AggregateSum();
                    }
                    else if("avg".equals(tokens[1]))
                    {
                        AggregateAvg();
                    }   
                }
            }
            else if(QuerySize == 7)
            {
                //delete from student where rid = RID
                //delete from student where name = Name
                if("delete".equals(tokens[0]))
                {
                    if("from".equals(tokens[1]))
                    {
                        if("student".equals(tokens[2]))
                        {
                            if("where".equals(tokens[3]))
                            {
                                if("rid".equals(tokens[4]))
                                {
                                    DeleteSpecific(Integer.parseInt(tokens[6]));
                                }
                                else if("name".equals(tokens[4]))
                                {
                                    DeleteSpecific(tokens[6]);
                                }
                            }
                        }
                    }
                }
            }
            else if(QuerySize == 8)
            {
                //select * from student where salary > Salary
                //select * from student where salary < Salary
                //select * from student where rid > RID
                //select * from student where rid < RID
                if("select".equals(tokens[0]))
                {
                    if("salary".equals(tokens[5]))
                    {
                        if(">".equals(tokens[6]))
                        {
                            GreaterSalaryRange(Integer.parseInt(tokens[7]));
                        }
                        else if("<".equals(tokens[6]))
                        {
                            SmallerSalaryRange(Integer.parseInt(tokens[7]));
                        }
                    }
                    else if("rid".equals(tokens[5]))
                    {
                        if(">".equals(tokens[6]))
                        {
                            GreaterRIDRange(Integer.parseInt(tokens[7]));
                        }
                        else if("<".equals(tokens[6]))
                        {
                            SmallerRIDRange(Integer.parseInt(tokens[7]));
                        }
                    }
                }

                //update salary to Salary where rid = RID
                //update name to Name where rid = RID
                if("update".equals(tokens[0]))
                {
                    if("salary".equals(tokens[1]))
                    {
                        UpdateSalary(Integer.parseInt(tokens[7]), Integer.parseInt(tokens[3])); // update only with RID and not Name because Names can be duplicates
                    }
                    else if("name".equals(tokens[1]))
                    {
                        UpdateName(Integer.parseInt(tokens[7]), tokens[3]);
                    }
                }
            }
        }
    }   

    // Insert into Query
    public void InsertData(String str, int value) // name and salary 
    {
        Student sobj = new Student(str, value);
        lobj.add(sobj);
    }

    // Display All without condition
    public void DisplayAll()
    {
        for(Student sref : lobj)
        {
            sref.DisplayData();
        }
    }

    // Display by RID
    public void DisplaySpecific(int rid)
    {
        for(Student sref : lobj)
        {
            if(sref.RID == rid)
            {
                sref.DisplayData();
                break;
            }
        }
    }

    // Display by Name
    public void DisplaySpecific(String str)
    {
        for(Student sref : lobj)
        {
            if(str.equals(sref.Name))
            {
                sref.DisplayData(); 
                // break; not needed because duplicates can be there of name
            }
        }
    }

    // Delete by RID
    public void DeleteSpecific(int rid)
    {
        int index = 0;

        for(Student sref : lobj)
        {
            if(sref.RID == rid)
            {
                lobj.remove(index);
                break;
            }
            index++;
        }
    }

    // Delete by Name
    public void DeleteSpecific(String str)
    {
        int index = 0;

        for(Student sref : lobj)
        {
            if(str.equals(sref.Name))
            {
                lobj.remove(index);
                // break; not needed because duplicates can be there of name
            }
            index++;
        }
    }

    // Display Maximum Salary
    public void AggregateMax()
    {
        int iMax = 0;
        Student temp = null;    // reference to get data

        for(Student sref : lobj)
        {
            if(sref.Salary > iMax)
            {
                iMax = sref.Salary;
                temp = sref;
            }
        }
        System.out.println("Information of Student having maximum salary is : ");
        temp.DisplayData();
    }

    // Display Minimum Salary
    public void AggregateMin()
    {
        int iMin = (lobj.getFirst()).Salary;
        Student temp = lobj.getFirst();

        for(Student sref : lobj)
        {
            if(sref.Salary < iMin)
            {
                iMin = sref.Salary;
                temp = sref;
            }
        }
        System.out.println("Information of Student having minimum salary is : ");
        temp.DisplayData();
    }

    // Display Sum of all salaries
    public void AggregateSum()
    {
        long iSum = 0;
        
        for(Student sref : lobj)
        {
            iSum = iSum + sref.Salary;
        }

        System.out.println("Summation of all salaries is : "+iSum);
    }

    // Display Average salary
    public void AggregateAvg()
    {
        long iAvg = 0;

        for(Student sref : lobj)
        {
            iAvg = iAvg + sref.Salary;
        }

        System.out.println("Avergae salary is : "+iAvg/(lobj.size()));
    }

    // Count number of records
    public void AggregateCount()
    {
        System.out.println("Total number of Records are : "+lobj.size());
    }

    // Displays records where Salary is greater than iValue
    public void GreaterSalaryRange(int iValue)
    {
        Student temp = null;

        for(Student sref : lobj)
        {
            if(sref.Salary > iValue)
            {
                temp = sref;
                temp.DisplayData();
            }
        }
    }

    // Displays records where Salary is smaller than iValue
    public void SmallerSalaryRange(int iValue)
    {
        Student temp = null;

        for(Student sref : lobj)
        {
            if(sref.Salary < iValue)
            {
                temp = sref;
                temp.DisplayData();
            }
        }
    }

    // Displays records where RID is greater than iValue
    public void GreaterRIDRange(int rid)
    {
        Student temp = null;

        for(Student sref : lobj)
        {
            if(sref.RID > rid)
            {
                temp = sref;
                temp.DisplayData();
            }
        }
    }

    // Displays records where RID is smaller than iValue
    public void SmallerRIDRange(int rid)
    {
        Student temp = null;

        for(Student sref : lobj)
        {
            if(sref.RID < rid)
            {
                temp = sref;
                temp.DisplayData();
            }
        }
    }

    // Updates and displays salary on given RID
    public void UpdateSalary(int rid, int iValue)
    {
        Student temp = null;

        for(Student sref : lobj)
        {
            if(sref.RID == rid)
            {
                sref.Salary = iValue;
                temp = sref;
            }
        }
        System.out.println("Updated record with salary is : ");
        temp.DisplayData();
    }

    // Updates and displays name on given RID
    public void UpdateName(int rid, String str)
    {
        Student temp = lobj.getFirst();

        for(Student sref : lobj)
        {
            if(sref.RID == rid)
            {
                sref.Name = str;
                temp = sref;
            }
        }
        System.out.println("Updated record with name is : ");
        temp.DisplayData();
    }    
}

class CDBMS
{
    public static void main(String arg[])
    {
        DBMS dobj = new DBMS();
        
        dobj.StartDBMS();
    }
}
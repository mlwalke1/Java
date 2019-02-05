#include "student.h"
#include <iomanip>

void initialize_std_list(StudData[], int&);
void print_std_list(const StudData[], int);
void print_avg(const StudData[], int);
char grade_conv(float score);

int main()
{
  StudData stdList[NUM_STDS];
  int num;                              // number of input

  initialize_std_list(stdList, num);    // initializes the item table
  print_std_list(stdList, num);        	// print the read data
  print_avg(stdList, num);

  return 0;
}

//READS input from text file and INITIALIZES StudData array objects
void initialize_std_list(StudData students[], int& num)
{
  ifstream infile;
  int id;
  string name;
  float grade;

  // read items data from an input file
  infile.open("student.txt");
  if (!infile) {
    cout << "Cannot open the input file!" << endl;
    exit(1);
  }

  num = 0;
  infile >> id;                         // priming read

  // reads name, and grades of each student in the file and stores in array
  while(infile) {
    students[num].setID(id);
    infile >> name;
    students[num].setName(name);
    infile >> grade;
    students[num].setEng101(grade);
    infile >> grade;
    students[num].setHist201(grade);

    num++;
    infile >> id;
  }

  infile.close();
}

// FORMATS and PRINTS StudData array to screen.
void print_std_list(const StudData students[], int num)
{
  printf( "%-20s %-20s %-10s %10s\n" , "Student Name" , "Student ID" , "Eng101", "Hist201" );
  printf( "%-20s %-20s %-10s %10s\n" , "------------" , "----------" , "------", "-------" );
  char grade1;
  char grade2;
  for(int i = 0; i < num; i++) {
	grade1 = grade_conv(students[i].getEng101());
	grade2 = grade_conv(students[i].getHist201());
    cout << setw(10) << students[i].getName() << "        ";
	cout << setw(10) << students[i].getID() << "            ";
    cout << setw(6) << students[i].getEng101() << "(" << grade1 << ")" <<  "     ";
    cout << setw(6) << students[i].getHist201() << "(" << grade2 << ")" << endl ;
  }
}

// CALCULATES average scores from StudData array and number of students and PRINTS them
// to the screen.
void print_avg(const StudData students[], int num)
{
	
  float total1 = 0;
  float total2 = 0;
  float avg1 = 0;
  float avg2 = 0;
  
  for(int i = 0; i < num; i++) {
	  total1 += students[i].getEng101();
	  total2 += students[i].getHist201();
  }
  avg1 = total1 / num;
  avg2 = total2 / num;
  
  cout << endl;
  cout << "                        " << "Class Average:      " << avg1 << "       " << avg2 <<endl;
}


// CALCULATES a letter grade from a raw score and returns it.
char grade_conv(float score) {
	char grade;

	if(score >= 90){
		grade = 'A';
	}
    else if(score < 90 && score >= 80) {
		grade = 'B';
	}	
	else if(score < 80 && score >= 70) {
		grade = 'C';
	}	
	else if(score <= 70 && score >= 60) {
		grade = 'D';
	}	
	else if(score < 60) {
		grade = 'F';
	}	
	return grade;
}  


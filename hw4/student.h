// Program 4: Student grade program
// Course:  CIS 357
// Instructor: Il-Hyung Cho
// Student: Mathew Walker
// Email: mlwalke1@svsu.edu
// Program Description:
// Program reads a text file containing student Name, ID, and scores for English and History. The program then prints 
// scores in a formatted table, give a letter grade for each score, and averages the scores of each student at the end
// of the table.


#include <iostream>
#include <fstream>
#include <cstdlib>


using namespace std;

const int NUM_STDS = 10;

class StudData {
public:
   void setID(int);
   void setName(string);
   void setEng101(float);
   void setHist201(float);
   int  getID () const;
   string getName() const;
   float getEng101() const;
   float getHist201() const;
   float showGPA();

private:
   int _id;                     // student number
   string _name;                // student name;
   float _eng101;               // English score
   float _hist201;              // History score
};



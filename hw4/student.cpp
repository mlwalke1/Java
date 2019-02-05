// student.cpp

#include "student.h"

void StudData::setID(int id) {
  _id = id;
}

void StudData::setName(string name) {
  _name = name;
}

void StudData::setEng101(float score) {
  _eng101 = score;
}

void StudData::setHist201(float score) {
  _hist201 = score;
}

int StudData::getID() const {
  return _id;
}

string StudData::getName() const {
  return _name;
}

float StudData::getEng101() const {
  return _eng101;
}

float StudData::getHist201() const {
  return _hist201;
}


#include "vector3.h"

std::set<Vector3 *> Vector3::_vector3List;

Vector3::Vector3(float x, float y, float z)
{
	X = x;
	Y = y;
	Z = z;
	//std::cout << "Vector3 Constructor Called" << endl;
	_vector3List.insert(this);
}

Vector3::~Vector3()
{
	_vector3List.erase(this);
}

std::set<Vector3 *> Vector3::getVectorList()
{
	return _vector3List;
}

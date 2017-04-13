#ifndef VECTOR3_H
#define VECTOR3_H

#include <iostream>
#include <set>

using namespace std;

class Vector3
{
	private:
		static std::set<Vector3 *> _vector3List;
		//static Vector3 * _vector3List2;
	public:
		Vector3(float x = 0.0, float y = 0.0, float z = 0.0);
		~Vector3();
		static std::set<Vector3 *> getVectorList();
		float X;
		float Y;
		float Z;
};

#endif

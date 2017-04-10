#include "player.h"
#include "vector3.h"
#include <iostream>

using namespace std;

int main()
{
	Vector3 vec1(5, 2, 4);
	Vector3 vec2(10, 4, 8);
	Vector3 vec3(15, 6, 12);
	Vector3 vec4(20, 8, 16);

	/*for(Vector3 vec : Vector3::getVectorList())
	{
		cout << "X: " << vec.X << ", Y: " << vec.Y << ", Z: " << vec.Z << endl;
	}*/
	std::set<Vector3 *> vectorList = Vector3::getVectorList();
	for(std::set<Vector3 *>::iterator it = vectorList.begin(); it != vectorList.end(); ++it)
	{
		cout << "X: " << (*it)->X << ", Y: " << (*it)->Y << ", Z: " << (*it)->Z << endl;
	}

	/*cout << "X: " << vec.X << endl;
	cout << "Y: " << vec.Y << endl;
	cout << "Z: " << vec.Z << endl;*/
	return 0;
}

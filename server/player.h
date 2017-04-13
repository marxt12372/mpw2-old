#ifndef PLAYER_H
#define PLAYER_H

#include "vector3.h"

#include <iostream>
#include <set>

using namespace std;

class Player
{
	private:
		static std::set<Player *> _playerList;
		Vector3 _position;
		Vector3 _rotation;
		char * _name;
	public:
		Player(char * name);
		~Player();
		void setPosition(Vector3 position);
		Vector3 getPosition();
		void setRotation(Vector3 rotation);
		Vector3 getRotation();
		char * getName();
		static std::set<Player *> getPlayerList();
};

#endif

#ifndef PLAYER_H
#define PLAYER_H

#include "vector3.h"

#include <stdio.h>
#include <string.h>
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
		char * _ip;
	public:
		Player(char * name, char * ip);
		~Player();
		void setPosition(Vector3 position);
		Vector3 getPosition();
		void setRotation(Vector3 rotation);
		Vector3 getRotation();
		char * getName();
		static std::set<Player *> getPlayerList();
		static Player * getPlayerFromName(char * name);
};

#endif

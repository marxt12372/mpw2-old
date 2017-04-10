#ifndef PLAYER_H
#define PLAYER_H

#include <iostream>
#include <set>

using namespace std;

class Player
{
	private:
		static std::set<Player *> _playerList;
	public:
		Player();
		~Player();
		static std::set<Player *> getPlayerList();
};


#endif

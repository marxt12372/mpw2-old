#include "player.h"

std::set<Player *> Player::_playerList;

Player::Player()
{
	_playerList.insert(this);
}

Player::~Player()
{
	_playerList.erase(this);
}

std::set<Player *> Player::getPlayerList()
{
	return _playerList;
}

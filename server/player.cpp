#include "player.h"

std::set<Player *> Player::_playerList;

Player::Player(char * name, char * ip)
{
	_name = name;
	_ip = ip;
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

Player * Player::getPlayerFromName(char * name)
{
	for(auto player : Player::getPlayerList())
	{
		if(strcmp(player->getName(), name) == 0)
		{
			return player;
		}
	}
	return NULL;
}

void Player::setPosition(Vector3 position)
{
	_position = position;
}

Vector3 Player::getPosition()
{
	return _position;
}

void Player::setRotation(Vector3 rotation)
{
	_rotation = rotation;
}

Vector3 Player::getRotation()
{
	return _rotation;
}

char * Player::getName()
{
	return _name;
}

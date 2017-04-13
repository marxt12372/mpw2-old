#include "player.h"
#include "vector3.h"
#include <iostream>
#include <thread>
#include <stdio.h>
#include <stdlib.h>
#include <syslog.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <sys/time.h>
#include <signal.h>
#include <string.h>
#include <errno.h>
#include <unistd.h>
#include <stdio.h>
#include <arpa/inet.h>

using namespace std;

//void connectionThread();
void updatingThread();

struct sockaddr_in myaddr;
struct sockaddr_in remaddr;
socklen_t addrlen = sizeof(remaddr);
int fd;

int main()
{
	//std::thread updateThread(updatingThread);

	if((fd = socket(AF_INET, SOCK_DGRAM, 0)) < 0)
	{
		perror("Cannot create socket\n");
		return 0;
	}

	int yes = 1;
	setsockopt(fd, SOL_SOCKET, SO_REUSEADDR, &yes, sizeof(yes));
	memset((char *) &myaddr, 0, sizeof(myaddr));
	myaddr.sin_family = AF_INET;
	myaddr.sin_addr.s_addr = htonl(INADDR_ANY);
	myaddr.sin_port = htons(9667);

	if(bind(fd, (struct sockaddr *) &myaddr, sizeof(myaddr)) < 0)
	{
		perror("Bind failed\n");
		return 0;
	}

	char buffer[256];
	int recvlen;

	/*while(true)
	{
		recvlen = recvfrom(fd, buffer, sizeof(buffer), 0, (struct sockaddr *) &remaddr, &addrlen);
		//printf("%s>%s\n", inet_ntoa(remaddr.sin_addr), buffer);
		char name[32];
		float x, y, z, rx, ry, rz;
		if(std::sscanf(buffer, "conn|%s", &name))
		{
			printf("New connection from %s, with the name %s\n", inet_ntoa(remaddr.sin_addr), name);
			Player newPlayer((char *) name);
		}
		else if(std::sscanf(buffer, "move %s to %f,%f,%f,%f,%f,%f", &name, &x, &y, &z, &rx, &ry, &rz))
		{
			printf("%s moved to: %f %f %f %f %f %f\n", name, x, y, z, rx, ry, rz);
		}
		bzero(buffer, sizeof(buffer)-1);
	}*/

	//updateThread.join();

	/*port = atoi(9667);
	sockfd = socket(AF_INET, SOCK_STREAM, 0);
	if(sockfd < 0)
	{
		printf("Error creating socket.");
		exit(0);
	}*/

	/*std::thread connThread(connectionThread);
	std::thread updateThread(updatingThread);

	connThread.join();
	updateThread.join();*/

	Vector3 vec1(5, 2, 4);
	Vector3 vec2(10, 4, 8);
	Vector3 vec3(15, 6, 12);
	Vector3 vec4(20, 8, 16);

	for(auto vec : Vector3::getVectorList())
	{
		cout << "X: " << vec->X << ", Y: " << vec->Y << ", Z: " << vec->Z << endl;
		Vector3 vec222(vec->X+vec->Y, 2, 4);
		cout << "X: " << vec222.X << ", Y: " << vec222.Y << ", Z: " << vec222.Z << endl;
		usleep(1000000);
		//cout << "X: " << (*it)->X << ", Y: " << (*it)->Y << ", Z: " << (*it)->Z << endl;
	}

	Vector3 vec5(25, 10, 20);
	Vector3 vec6(30, 12, 24);

	for(auto vec : Vector3::getVectorList())
	{
		cout << "X: " << vec->X << ", Y: " << vec->Y << ", Z: " << vec->Z << endl;
		//cout << "X: " << (*it)->X << ", Y: " << (*it)->Y << ", Z: " << (*it)->Z << endl;
	}
	/*std::set<Vector3 *> vectorList = Vector3::getVectorList();
	for(std::set<Vector3 *>::iterator it = vectorList.begin(); it != vectorList.end(); ++it)
	{
		cout << "X: " << (*it)->X << ", Y: " << (*it)->Y << ", Z: " << (*it)->Z << endl;
	}*/

	/*cout << "X: " << vec.X << endl;
	cout << "Y: " << vec.Y << endl;
	cout << "Z: " << vec.Z << endl;*/
	return 0;
}

void updatingThread()
{
	while(true)
	{
		std::set<Player *> playerList = Player::getPlayerList();
		cout << "Players:" << endl;
		for(auto player : Player::getPlayerList())
		{
			cout << "asd" << endl;
			//cout << "X: " << vec.X << ", Y: " << vec.Y << ", Z: " << vec.Z << endl;
		}
		/*for(std::set<Player *>::iterator player = playerList.begin(); player != playerList.end(); ++player)
		{
			cout << (*player)->getName() << endl;
		}*/
		usleep(1000000);
	}
}

/*void connectionThread()
{
	//std::cout << "Yoyo" << endl;
	while(true)
	{
		std::cout << "connThread" << endl;
		usleep(1000000);
	}
}*/

#include "main.h"

int main(int argc, char * argv[])
{
	int listenfd, connfd;
	struct timeval timeout;
	struct sockaddr_in servaddr, cliaddr;
	socklen_t clilen;
	char buffer[64];
	int n;

	if((listenfd = socket(AF_INET, SOCK_STREAM, 0)) < 0)
	{
		printf("Socket creation failed");
		return 0;
	}

	int yes = 1;
	setsockopt(listenfd, SOL_SOCKET, SO_REUSEADDR, &yes, sizeof(yes));
	memset((void *) &servaddr, '\0', (size_t) sizeof(servaddr));
	servaddr.sin_family = AF_INET;
	servaddr.sin_addr.s_addr = INADDR_ANY;
	servaddr.sin_port = htons(35000);

	if(bind(listenfd, (struct sockaddr *) &servaddr, sizeof(servaddr)) < 0)
	{
		printf("Bind Error");
		return 0;
	}

	if(listen(listenfd, 5) < 0)
	{
		printf("Listen Error");
		return 0;
	}

	while(1)
	{
		clilen = sizeof((struct sockaddr *) &cliaddr);
		if((connfd = accept(listenfd, (struct sockaddr *) &cliaddr, &clilen)) < 0)
		{
			printf("Accept Error.\n");
			printf("Error: %d\n", errno);
			continue;
		}
		while(read(connfd, buffer, 63) > 0)
		{
			printf("Buffer: %s\n", buffer);
			bzero(buffer, 63);
		}
		usleep(100000);
	}
	return 1;
}

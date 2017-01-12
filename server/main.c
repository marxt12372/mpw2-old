#include "main.h"

int main(int argc, char * argv[])
{
	int listenfd;
	struct timeval timeout;
	struct sockaddr_in servaddr, cliaddr;
	socklen_t cliaddrlen;
	size_t nbytes;
	char buffer[512];
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
	servaddr.sin_port = htons(9667);

	if(bind(listenfd, (struct sockaddr *) &servaddr, sizeof(servaddr)) < 0)
	{
		printf("Bind Error");
		return 0;
	}

	while(1)
	{
		/*clilen = sizeof((struct sockaddr *) &cliaddr);
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
		}*/
		if(recvfrom(listenfd, buffer, sizeof(buffer), nbytes, 0, cliaddr, cliaddrlen))
		{
			
		}
		usleep(100000);
	}
	return 1;
}

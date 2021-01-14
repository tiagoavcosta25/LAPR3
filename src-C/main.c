/* print files in current directory with specific file extension */
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <dirent.h>
#include <string.h>
#include <unistd.h>
#include "estimate.h"
//https://stackoverflow.com/a/25885587
//https://www.tutorialspoint.com/c_standard_library


/* when return 1, scandir will put this dirent to the list */
static int parse_ext(const struct dirent *dir) {
	if(!dir)
		return 0;

	//searches for last ocurrence of character '.'
	const char *ext = strrchr(dir->d_name,'.');
	if((!ext) || (ext == dir->d_name))
		return 0;
	else {

		//compares strings and returns 0 if both are identical
		if(strcmp(ext, ".flag") == 0)
			return 1;
		}
	return 0;
}

int main(void) {
	struct dirent **namelist;
	FILE *lock_file;
	FILE *estimate_file;
	FILE *estimate_flag_file;
	int i;
	int skip = 0;
	int n, count;
	int capacity = 0, current = 0, charge = 0;
	int time_charge_multiplied = 0;
	double time_charge = 0;
	char *foo;
	char *ptr;
	char lock_file_name[90] = "";
	char estimate_file_name[60];
	system("clear");
	for(;;) {
		//check for .flag files on dir
		n = scandir("lock_files", &namelist, parse_ext, alphasort);
		count = n;
		if (n < 0) {
			perror("scandir");
			return 1;
		}
		else {
			while (n--) {
				//the C library function char *strchr(const char *str, int c) searches for the first occurrence of the character c (an unsigned char) in the string pointed to by the argument str.
				ptr = strchr(namelist[n]->d_name, '.');
				if(ptr != NULL) {
					*ptr = '\0';
				}
			}
		}

		if(count > 0) {
			sprintf(lock_file_name,"lock_files/%s.data",namelist[0]->d_name);
			if ((lock_file = fopen(lock_file_name, "r")) == NULL) {
				system("clear");
				printf("File: %s does not exist, flag file deleted!\n", lock_file_name);
				strcat(lock_file_name, ".flag");
				remove(lock_file_name);
				skip = 1;
			}

			if(skip == 0) {

				fscanf(lock_file, "%d;%d;%d", &capacity, &current, &charge);
				fclose(lock_file);

				//calcute charge time
				time_charge_multiplied = estimate(capacity, current, charge);
				time_charge = ((float) (time_charge_multiplied)) / 10000;

				foo = namelist[0]->d_name;
				foo += 5;

				//get estimate file name
				sprintf(estimate_file_name,"estimate_files/estimate_%s.data", foo);

				//create estimate file
				if ((estimate_file = fopen(estimate_file_name, "w")) == NULL) {
					printf("Error creating estimate file!\n");
					skip = 1;
				}
			}

			if(skip == 0) {
				fprintf(estimate_file, "%f", time_charge);
				fclose(estimate_file);

				//get estimate flag file name
				strcat(estimate_file_name, ".flag");

				//create estimate flag file
				if ((estimate_flag_file = fopen(estimate_file_name, "w")) == NULL) {
					printf("Error creating estimate flag file!\n");
					skip = 1;
				}
			}

			if(skip == 0) {
				fclose(estimate_flag_file);

				//delete lock file
				if(remove(lock_file_name) != 0) {
					printf("Could not delete lock file!\n");
				}

				//delete lock flag file
				strcat(lock_file_name, ".flag");
				if(remove(lock_file_name) != 0) {
					printf("Could not delete lock flag file!\n");
				}
			}
		}
		else
			skip = 1;
		if(skip == 0) {
			foo = lock_file_name;
			foo += 11;
			foo[strlen(foo)-5] = '\0';
			system("clear");
			printf("File %s was handled successfully!\n", foo);
		}
		else
		skip = 0;

		//free dynamic memory
		if(n == 0) {
			free(namelist);
		}
		else {
			for(i = 0; i < n; i++) {
				free(namelist[n]);
			}
		}
		sleep(5);
	}
	return 0;
}

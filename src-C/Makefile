#Makefile
INCLUDES = estimate.h   
SOURCES = estimate.s main.c 
OBJFILES = estimate.o main.o
EXEC = main

#Suffix
.SUFFIXES : .c .s .o

#

.c.o: 
	gcc -Wall -g -c $<

.s.o: 
	gcc -Wall -g -c $<	

${EXEC}: ${OBJFILES}
		gcc -Wall -g -o ${EXEC} ${OBJFILES}

${OBJFILES}: ${SOURCES} ${INCLUDES}

run: ${EXEC}
	 ./${EXEC}
 
clean:
	rm -f ${OBJFILES} ${EXEC}
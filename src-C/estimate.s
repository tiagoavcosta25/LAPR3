.section .data
#int estimate(int capacity, int current, int charge)

.section .text
	.global estimate
	
estimate:
	#Prologue
	pushl %ebp 					# save previous stack frame pointer
	movl %esp , %ebp 			# the stack frame pointer for our function
	
	#Charging Time (h) = Battery Capacity (Ah) / Current charged [by the charging spot] (A)
	#Battery Capacity = b, Current charged [by the charging spot] = c, Current Charge = cc;
	#Charging Time (h) = (b×((100-cc)/100))/c = xx h
	
	movl 16(%ebp), %edx 		#--> c --> charge 
	movl $100, %eax 			#--> maximum possible charge
	movl %eax, %ecx 			#--> set %ecx to 100
	subl %edx, %eax 			#--> 100-cc --> charge % left to charge
	
	imull $10000, %eax 			#--> multiply by 10000 to get a more exact time since assembly doesnt allow floats(Divide in C module) 
	
	cdq							#--> prepare division
	idivl %ecx					#--> (100-cc)/100 --> divide (charge % left to charge) by 100
	
	movl 8(%ebp), %edx 			#--> b --> capacity 
	imull %edx, %eax			#--> b*((100-cc)/100) --> capacity multiplied by ((charge % left to charge) divided by 100)
	
	movl 12(%ebp), %ecx 		#--> c --> current
	cdq							#--> prepare division
	idivl %ecx					#--> charging time (h) --> (b×((100-cc)/100))/c --> (capacity multiplied by ((charge % left to charge) divided by 100)) divided by current
	
	#Epilogue
	movl %ebp , %esp 			# restore the stack pointer (" clear " the stack )
	popl %ebp 					# restore the stack frame pointer
	ret

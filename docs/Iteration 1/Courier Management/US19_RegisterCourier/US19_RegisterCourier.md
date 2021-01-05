# UC 7 - Register Freelancer

## 1. Requirements Engineering

### Brief format

The collaborator initializes the register of a new Freelancer. The system asks for the data that features the freelancer(i.e.,name,
level of expertise,  e-mail, NIF, bank account (IBAN) , address and country). The collaborator inserts the required data.
The system validates, presents the data and asks for a confirmation. The collaborator confirms. The system registers the Freelancer's data, creates a user for the Freelancer and informs the collaborator of the success of the operation.


### SSD
![UC2_SSD.svg](UC2_SSD.svg)


### Complete Format

#### Main Actor

Collaborator

#### Stakeholders and their interests
**collaborator:** wants to register the freelancer so him can access the platform.

**Freelancer:** wants to use the services provided by the platform.

**T4J:** wants the freelancer to be registered in the platform, so he can use the services provided by the platform.

#### Preconditions

n/a

#### Postconditions

* The freelancer data is saved in the Organization.

#### Main success scene (or basic flux)

1. The collaborator initializes the register of a new Freelancer.
2. The system asks for the data that features the freelancer(i.e., name, level of expertise,  e-mail, NIF, bank account (IBAN) , address and country).
3. The collaborator inserts the required data.
4. The system validates, presents the data and asks for a confirmation.
5. The collaborator confirms.
6. The system registers the Freelancer's data, creates a user for the Freelancer and informs the collaborator of the success of the operation.


#### Extensions (or alternative flux)

*a. The collaborator cancels the operation.

The use case finishes.

3b. Lacking minimum data requirements.
   1. The System alerts the collaborator of which of the inserted data are lacking minimum requirements.
   2. The System allows the collaborator to insert the missing data.
>
   2a. The collaborator does not change the inserted data. The use case finishes.

4c. The system notices the data (or any subset) inserted should be unique and are already registered in the System.
   1. The System alerts the collaborator of which data is failing to validate.
   2. The System allows the collaborator to insert the missing data.
>
   2a. The collaborator does not change the inserted data. The use case finishes.
   
#### Special requirements

#### Variation of technology and data list
\-

#### Frequency of Occurrence
\-

#### Open Questions

\-

## 2. OO Analysis

### Use Case's Fragment Domain Model

![UC2_DM.svg](UC2_DM.svg)


## 3. Design - Use Case Realization

### Rational

| Basic Flux | Question: What class... | Answer  | Explanation  |
|:--------------  |:---------------------- |:----------|:---------------------------- |
|1. The collaborator initializes the register of a new Freelancer |... interacts with the collaborator?| RegisterFreelancerUI |Pure Fabrication|
| |... coordinates the UC?| RegisterFreelancerController |Controller|
| |... creates instances of Freelancer?|FreelancerRegistration|Creator(rule1) mixed with HC+LC on the Platform.|
|2. The system asks for the data that features the freelancer(i.e., name, level of expertise,  e-mail, NIF, bank account (IBAN) , address and country).||||
|3. The collaborator inserts the required data. |... saves the inserted data?|Freelancer| IE: instance created in step 1|
| |... creates instance of Address?|Freelancer|creator(regra1)|
|4. The system validates, presents the data and asks for a confirmation.|... validates the Freelancer data (local validation)|Freelancer|IE: it has his own data|
| |... validates the Freelancer data (global validation)|FreelancerList|IE: The FreelancerList has registered Freelancers|
| |... has FreelancerList? | Organization | IE: Organization knows Freelancers |
|5. The collaborator confirms. ||||
|6. The system registers the Freelancer's data, creates a user for the Freelancer and informs the collaborator of the success of the operation.|... saves the created Freelancer?| Organization |IE: In the DM the Organization has Freelancers|
| |...informs the collaborator?|CreatePaymentTransactionUI|


### Systematization ##

 It follows from the rational that the conceptual classes promoted to software classes are:

 * Plataforma
 * Freelancer
 * Address

 Other identified software classes (i.e. Pure Fabrication)::  

 * RegisterFreelancerUI  
 * RegisterFreelancerController
 * FreelancerList
 

###	Sequence Diagram

![UC2_SD.svg](UC2_SD.svg)


###	Class Diagram

![CD_UC2.svg](UC2_CD.svg)


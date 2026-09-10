# AI review notes — Lab 10

## lab10-001 — weak vs strong (entity)



* &#x20;Date: 2026-09-09
* &#x20;Weak prompt used: // create customer class
* &#x20;Output summary: Copilot generated much of the Customer class structure, but the result was incomplete and depended heavily on workspace context.
* \- Strong prompt used: Create a plain Java Customer class for Northstar CRM with customerId, fullName, email, phone, status, and createdAt. Include constructors, getters/setters, equals/hashCode based on customerId, and toString. Java 21, no Spring, no JPA.
* \- Output summary: Copilot generated a complete Customer class with the requested fields and methods, but also added extra code such as a main method and invented sample data.
* \- Decision: partial
* \- Reason (1 sentence): The strong prompt produced mostly correct code, but I would edit out the extra main method and invented sample data before accepting it.

## lab10-002 — weak vs strong (addCustomer)



* Date: 2026-09-09
* Weak prompt used: add a customer
* Output summary: Copilot generated an addCustomer method that only added the customer to a list and also created extra classes and sample data.
* Strong prompt used: Method addCustomer(Customer customer) on CustomerService: reject if customerId is null/blank, reject duplicate customerId with IllegalStateException, otherwise store and return the customer.
* &#x20;Output summary: Copilot generated validation for null/blank IDs, duplicate checking, storage in an in-memory list, and returned the customer.
* Decision: partial
* Reason (1 sentence): The strong prompt produced the required business rules, but I would still review and remove unnecessary extra demo code before accepting.

## lab10-003 — CustomerStatus / Customer scaffold



Rejected JPA? yes



Notes:

\- CustomerStatus contains only PROSPECT, ACTIVE, SUSPENDED, CLOSED.

\- Customer uses String customerId.

\- No @Entity, @Id, @Column, Spring, or JPA imports were accepted.

\- equals/hashCode are based on customerId only.

## lab10-004 — CustomerService review

* Notes:
* \- addCustomer rejects null/blank customerId.
* \- addCustomer rejects duplicate customerId with IllegalStateException.
* \- findByCustomerId returns Optional<Customer>.
* \- updateStatus throws IllegalArgumentException for an unknown customer.
* \- No Spring annotations or JPA dependencies were added.
* \- Main confirmed Ravi changes from PROSPECT to ACTIVE.







1\. What real customer data did you avoid typing into Copilot Chat, and what did you use instead?

2\. If Copilot suggests code that looks copied from a known library/article, what would you do before accepting it?

3\. What is your rule for Copilot-generated code you do not fully understand?







1\. I avoided using real customer SSNs, passwords, production emails, or other sensitive information in Copilot Chat. I used the lab fixtures CUS-1001 and CUS-1002 instead.



2\. If Copilot suggests code that looks copied from a known library or article, I would review the source and license before accepting it and follow my team's policy.



3\. If Copilot generates code that I do not fully understand, I will not accept it until I can explain what it does and verify that it is correct and safe.


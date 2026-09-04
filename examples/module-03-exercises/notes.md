\# Banking domain notes



| Entity | Identity | Important attributes | Main responsibility |

| ------ | -------- | -------------------- | ------------------- |

| Customer | customerId | name, email, phone | Maintain customer profile |

| Account | accountNumber | owner, balance, accountType | Protect balance and perform deposits/withdrawals |

| Transaction | transactionId | account, type, amount, timestamp | Record one account operation |



\## Relationships



\- One Customer can own zero or more Accounts.

\- One Account belongs to exactly one Customer.

\- One Account can have many Transactions.

\- One Transaction belongs to exactly one Account.



\## Rules



\- An account balance cannot be changed directly from outside Account.

\- A deposit amount must be positive.

\- A withdrawal cannot exceed the allowed balance.



\## Design decision



Account should decide whether a withdrawal is valid because Account owns the balance and the rules that control how the balance can change. Main should only coordinate user interaction and call the appropriate Account methods.



Customer

&#x20;  |

&#x20;  | owns

&#x20;  v

Account

&#x20;  |

&#x20;  | has

&#x20;  v

Transaction







Main should not do this kind of thing conceptually:



check balance

subtract money

change account balance



Instead, Main should ask the Account:



account.withdraw(...)



and the Account decides whether that withdrawal is allowed.





\-------------------------------module 3 exer7-----------------------------------



\## SRP Single Responsibility Principle

Each class or method should have one clear responsibility and one main reason to change.





\## OCP Open/Closed Principle

Adding FrozenAccount did not require changes inside SavingsAccount or CurrentAccount.

&#x09;-OCP — Open/Closed Principle: you added FrozenAccount as a brand-new class. You did not have to rewrite SavingsAccount or 	CurrentAccount to make the frozen behavior work. You extended the system instead of modifying existing working classes.



\## LSP Liskov Substitution Principle

FrozenAccount can be used anywhere an Account is expected because withdraw still returns a boolean and leaves the balance unchanged when the withdrawal fails.

&#x09;-LSP — Liskov Substitution Principle: FrozenAccount can sit inside the same Account\[] as the other account types. The loop can 	still call: account.withdraw(20.00)

&#x09;without needing special logic for the frozen account. A frozen withdrawal just returns false and leaves the balance unchanged. It 	still behaves according to the Account contract.



\## ISP Interface Segregation Principle

Printable should stay small and focused so classes are not forced to implement methods like sendEmailReceipt() when they do not need them.

&#x09;-this comes from your earlier Printable interface:public interface Printable {

&#x20;   	void printDetails();}

&#x09;It is small and focused. A class implementing it only has to support printing. You are not forcing every class to implement 	unrelated methods it does not need.



\## DIP   Dependency Inversion Principle1

Using Account as the reference type makes it easier to swap between SavingsAccount, CurrentAccount, and FrozenAccount without changing the calling code.







\----------------------module 4 lab4 -----------------------





StackExample:

method calls create stack frames;

frames disappear when methods return.



HeapExample:

objects live on the heap;

references point to those objects.



ObjectLifecycle:

GC cares about whether a heap object is still reachable,

not whether one specific variable became null.



identity hash code = a JVM-provided number associated with an object instance,

useful for showing whether references point to the same or different objects.





\----------------module------------------------



list of data structure and the operation you can do on them


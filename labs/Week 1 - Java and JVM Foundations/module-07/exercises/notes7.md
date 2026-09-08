ArithmeticException = invalid arithmetic operation, such as integer division by zero.

NullPointerException = trying to use a method or field on a null reference.

ArrayIndexOutOfBoundsException = trying to access an array index that does not exist.

IllegalArgumentException → the input value is bad



IllegalStateException → the object’s current condition/state is bad

example:

class BankAccount {

&#x20;   private boolean closed = false;



&#x20;   void withdraw(double amount) {

&#x20;       if (closed) {

&#x20;           throw new IllegalStateException("Account is closed");

&#x20;       }



&#x20;       System.out.println("Withdrawing " + amount);

&#x20;   }

}



If the account is closed, calling:



account.withdraw(100);



throws IllegalStateException because the account’s current state does not allow withdrawals.

\-------------------------------------------------------------------------------------------------------------------------

### Try-with-resources



**Try-with-resources: A try statement that automatically closes resources such as files, streams, database connections, or sockets after they are used. reader.close(); done automatically!!**



### Throw vs Throws

throw → actually throws an exception at a specific point in the code.

throws → declares in a method signature that the method may pass an exception to its caller.



### throw new



new InsufficientFundsException(balance, amount)

→ create an exception object



throw

→ send that exception out of the method



### Propagation

exception was thrown here

accountLayer()



which was called by

serviceLayer()



which was called by

menuLayer()



which was called by

main()



Exception propagation: When a method does not catch an exception, the exception travels up the call stack to its caller until a matching catch block handles it.



If an exception is never caught, it propagates to the top of the call stack, the JVM prints a stack trace, and the thread usually terminates.


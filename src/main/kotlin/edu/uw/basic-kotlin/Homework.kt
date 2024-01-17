package edu.uw.basickotlin

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(arg: Any): String {
    when (arg) {
        is String -> {
            return if (arg == "Hello") "world" else "Say what?"
        }
        is Int -> {
            return when (arg) {
                0 -> "zero"
                1 -> "one"
                in 2..10 -> "low number"
                else -> "a number"
            }
        }
        else -> return "I don't understand"
    }
}

// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(lhs: Int, rhs: Int): Int = lhs + rhs

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(lhs: Int, rhs: Int): Int = lhs - rhs

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(lhs: Int, rhs: Int, op: (Int, Int) -> Int) = op(lhs, rhs)

// write a class "Person" with first name, last name and age
class Person(var firstName: String, var lastName: String, var age: Int)
{
    val debugString: String
        get() = "[Person firstName:$firstName lastName:$lastName age:$age]"
}

// write a class "Money"
class Money(val amount: Int, val currency: String)
{
     val allowedCurrencies = arrayOf("USD", "EUR", "CAN", "GBP")

    init {
        if (amount < 0)
            throw IllegalArgumentException("Illegal amount, cannot be negative")
        if (currency !in allowedCurrencies)
            throw IllegalArgumentException("Unrecognized currency")
    }

    fun convert(resultCurrency: String): Money {
        if (resultCurrency !in allowedCurrencies) {
            throw IllegalArgumentException("Invalid currency: $currency")
        }

        // I know that there is an less redundant way, I just kept getting errors so I just added all combinations
        val conversionRate = when (currency to resultCurrency) {
            "USD" to "GBP" -> 0.5
            "USD" to "EUR" -> 1.5
            "USD" to "CAN" -> 1.25
            "GBP" to "USD" -> 2.0
            "GBP" to "EUR" -> 3.0
            "GBP" to "CAN" -> 2.5
            "EUR" to "USD" -> 2.0 / 3.0
            "EUR" to "GBP" -> 1.0 / 3.0 
            "EUR" to "CAN" -> 1.25 / 1.5 
            "CAN" to "USD" -> 4.0 / 5.0 
            "CAN" to "GBP" -> 2.0 / 5.0 
            "CAN" to "EUR" -> 3.0 / 5.0
            else -> 1.0
        }
        return Money((amount * conversionRate).toInt(), resultCurrency)
}

    operator fun plus(other: Money): Money {
        val converted = other.convert(this.currency)
        return Money(this.amount + converted.amount, this.currency)
    }
}
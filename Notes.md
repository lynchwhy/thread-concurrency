# Notes

- asked copilot about what data structure it would recommend to store the parsed data and it suggested a hash map which makes almost no sense. Likely poor prompting on my behalf.
When I disagreed and explained reasoning further, it gave me a better answer.

- i gave it the instructions to implement without sleeping threads to simulate time, but it did it anyway. see screeenshot.

- copilot has a tendency to suggest massive change of implementation which can be confusing and overwhelming at times.

- i learnt about the ? ternary if else operator
- i learnt about while (true) condition for running threads 




1. add coffee orders to queue
2. create 3 dispenser threads that simultaneously serve coffee, either hot or cold until the next order in the queue is of the other type.
3. the machine finishes all the current orders of the type, then switches type.

dispenser run()
    serve coffee

    if queue.next == currentMode 
        serve
    else
        wait


    coffeemachine()
        if all dispensers are finished
            change mode



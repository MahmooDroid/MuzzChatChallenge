# MuzzChatChallenge

[Muzz Chat Challenge.webm](https://github.com/MahmooDroid/MuzzChatChallenge/assets/113989273/ede0c637-8ca4-4a51-a6f1-a938d0f9e5ff)

Muzz Chat Challenge

Intro

To use the application the default setting is “Sender”. The menu option can be used at the top right to switch to “Receiver” and simulate receiving messages. To test the section feature the device time can be toggled. Once a message is sent the section will appear. 

Architecture

I have decided to implement features using Jetpack compose, Flow, Couroutines, HILT and Room. I have attempted to use the MVVM clean architecture pattern by breaking my application up into layers such as Data, Domain and Presentation. 

The data layer contains implementation of my repository and room database. 

The domain layer contains my interfaces which are implemented in the domain layer. My business logic is also contained in my usecase package.

My presentation layer contains all my compose components required to build the application. I use the mvvm pattern in this layer to allow for better testability as I inject my usecases into my viewmodel using hilt.

Improvements

I am still learning Jetpack compose and will continue to do so to learn the best practices and approaches.

Some improvements can be made on the UI side of things. I would have liked to have utilised material 3 properly and implemented a dark theme. I would have liked to make it more visually appealing and even added support for accessibility. 

Espresso instrumentation tests also could have been added to test the UI.

I would also improve the application by adding a Results sealed class. This would help error handling and make checking if flows have returned successfully or with an error. This would be super useful when using apis.



![image](https://github.com/MahmooDroid/MuzzChatChallenge/assets/113989273/fbed86b8-609a-4d15-aa5f-44b8f44db88a)

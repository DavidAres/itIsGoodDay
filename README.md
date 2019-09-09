# itIsGoodDay
Example with MVVM, Retrofit, Koin, Coroutines components


The main objective for "Is a good day application?" is to show if the current day is good for the user or not, the user has to configure what is a good day for him, after that, the app send a request to http://www.forecast.io with the city params and return all the weather information. The app check if the recover data is similar to the user settings, if it is ok a sun will appear on the home screen saying that it is a good day, in other case, a rainy cloud will appear and the day will be consider as a bad day.

The structure for this app is MVVM
For the dependencies inject I use Koin (https://insert-koin.io/), it is easy to understand and easy to implement.
For the services request I chose retrofit (https://square.github.io/retrofit/) library, and all the request are manage with coroutines.

<h1 align="center">Sample_Video</h1>

<p align="center">  
APP based on MVVM architecture.
</p>
</br>
<p align="center"> 

<img src=https://user-images.githubusercontent.com/78771861/235232521-e26f7e92-1122-4b76-9ce7-cb3b3bf20f63.gif align="right" />

## Tech stack & Open-source libraries
- Minimum SDK level 21
- [Kotlin](https://kotlinlang.org/) based , [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)  for asynchronous.

  - Lifecycle: Observe Android lifecycles and handle UI states upon the lifecycle changes.
  - ViewModel: Manages UI-related data holder and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
  - DataBinding: Binds UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
  - App is using the [PixabayAPI]([pixabay.com](https://pixabay.com/api/docs/)) for constructing RESTful API.<br>
 
- Architecture
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
  - Repository Pattern
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit): Construct the REST APIs and paging network data.
- [Exoplayer](https://developer.android.com/codelabs/exoplayer-intro#0): Create an ExoPlayer instance, which prepares and plays media.
## Architecture
 based on the MVVM architecture and the Repository pattern, which follows the [Google's official architecture guidance](https://developer.android.com/topic/architecture).


The overall architecture of **Newify** is composed of two layers; the UI layer and the data layer. Each layer has dedicated components and they have each different responsibilities, as defined below:

**Newify** was built with [Guide to app architecture](https://developer.android.com/topic/architecture), so it would be a great sample to show how the architecture works in real-world projects.


### Architecture Overview


- Each layer follows [unidirectional event/data flow](https://developer.android.com/topic/architecture/ui-layer#udf); the UI layer emits user events to the data layer, and the data layer exposes data as a stream to other layers.
- The data layer is designed to work independently from other layers and must be pure, which means it doesn't have any dependencies on the other layers.

With this loosely coupled architecture, you can increase the reusability of components and scalability of your app.

### UI Layer

![figure2](https://user-images.githubusercontent.com/78771861/234374929-0b5b1265-10fd-47c6-8cb6-acd49c16a9df.png)

The UI layer consists of UI elements to configure screens that could interact with users and [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) that holds app states and restores data when configuration changes.
- UI elements observe the data flow via [DataBinding](https://developer.android.com/topic/libraries/data-binding), which is the most essential part of the MVVM architecture. 

### Data Layer


![figure3](https://user-images.githubusercontent.com/78771861/234375003-f97e3da8-2fc3-4606-9a23-d118fb354079.png)

The data Layer consists of repositories, which include business logic, such as querying data from the local database and requesting remote data from the network. It is implemented as an offline-first source of business logic and follows the [single source of truth](https://en.wikipedia.org/wiki/Single_source_of_truth) principle.<br>
  

# Baims Task

## Task Estimation
I have attached an Excel sheet that contains the detailed estimation for the task. The estimation includes the breakdown of the time needed for different phases of the project, such as:

- **UI Implementation**
- **API Integration**
- **Caching**
- **Error Handling**

You can find the estimation details in the attached Excel file: [Baims_Task_Estimation.xlsx](https://github.com/AhmedSamirScience/Biams_Task/blob/master/Baims_Task_Estimation.xlsx).

## Tutorial Video
To facilitate understanding of the application, I have included a video tutorial demonstrating how to use the app. This video walks through the features, UI interactions, and key functionalities of the app. You can access the following link: [Video Link](https://github.com/AhmedSamirScience/Biams_Task/blob/master/tutorial_video.mp4).

## Technologies Used
The following technologies and methodologies were used in this project:

- **Dependency Management**: Efficient management of dependencies.
- **Encryption and Decryption**: Implemented for securing local data.
- **Nav Graph**: Used for navigation within the app.
- **Model-View-ViewModel (MVVM)**: Architectural pattern used for separating concerns.
- **Retrofit**: For API calls and network communication.
- **StateFlow & Coroutines**: For handling asynchronous data streams and concurrency.
- **Dagger Hilt**: Dependency injection framework for managing app components.
- **Shared Preferences**: For storing simple data locally.
- **ProGuard**: Enabled to optimize and obfuscate the code.
- **Gradle Properties**: Base URLs are accessed directly from Gradle properties.
- **Base Classes**: Implemented base classes such as `BaseFragment`, `BaseViewModel`, and `BaseActivity` for reusability.
- **Clean Architecture**: Implemented separating concerns across different layers such as Mapper, Domain Layer, Use Cases, Data Layer, and Presentation Layer.

## Git Flow
I followed a structured Git Flow process throughout the development:

- **Master**: Main branch for production-ready code.
- **Dev**: Development branch where new features are integrated.
- **Feature Branches**: Each feature is developed in a separate branch and merged into Dev.
- **Release Tags**: Used for marking specific points in the development process.
- **Hotfix Branches**: For quick fixes to production code.

A screenshot of the Git history visualizing the flow of commits across different branches is included below:

![Git Flow Screenshot](https://github.com/AhmedSamirScience/Biams_Task/blob/master/Screenshot_of_the_Git_version_using_GitFlow.png)

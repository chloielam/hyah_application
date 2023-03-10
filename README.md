# HYAH - Your Favorite Places

HYAH is an Android application that allows users to save their favorite places with a title, image, and description. The application uses bottom navigation and has three main windows: a map window, a list of locations, and a user profile. Users can view markers of their saved locations on a map, view and edit a list of their saved locations, and view and edit their own profile. Data is stored in a JSON file.

## Installation

To run the project:
1. Open Android Studio and open the build.gradle file. 
2. Make sure you have the latest version of Kotlin and Android Studio installed. 
3. Sync the project and build it. 
4. You can then run the application on an emulator or an Android device.

## Usage
- Use the bottom navigation to switch between the map, list, and profile windows.
- In the map window, you can view markers of your saved locations on a map.
- In the list window, you can view and edit a list of your saved locations. You can also use the floating button to add more locations. When adding or editing you can search for a specific location or drag the marker with Google Maps.
- In the profile window, you can view and edit your name, username, pronouns, birthday, description, and avatar. You can select a date for birthday using date picker.

<table>
  <tr>
    <td>
      <img src="map_activity.png" width="230" height="440"/>
      <p align="center">Map Activity</p>
    </td>
    <td>
      <img src="list_activity.png" width="230" height="440"/>
      <p align="center">List Activity</p>
    </td>
    <td>
      <img src="profile_activity.png" width="230" height="440"/>
      <p align="center">Profile Activity</p>
    </td>
  </tr>
</table>

## Future Work

- Add login and add friend function.
- Change to firebase database.
- Include sharing current position between different users.

## Credits

This project is based on the Placemark project from the Android Programming with Kotlin course taught by Professor Drohan.

## Contribution

If you would like to contribute to this project, please submit a pull request with your proposed changes.

## License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT)

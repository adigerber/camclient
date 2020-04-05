# CamClient
CamClient is a Java-based client for security cameras manufactured by TVT.  
The main purpose of CamClient is to provide a working GUI/CLI for operating systems other than MacOSX/Windows/Android/iOS.

## Project Status
This project is an on-going effort developed during my free time.  
It is being developed and tested against a security camera sold by ProvisionISR which uses the DVR3 protocol (server type 9).  

In its current status, the client is capable of:
* **Discovery** - Connect to a TVT camera's HTTP server to discover which security camera is running
* **Authentication** - Authenticate against the security camera with username/password

The following protocols are supported:
* DVR3

The following protocols are not (yet) supported:
* NewCard
* DVRDVS
* IPCamera
* DVR4
* NVMS

## Project Structure
The project is divided into 3 sub-projects:

* **camclient-cli** - A CLI client; currently, does not do anything significant, but in the future it will be able to perform offline recording
* **camclient-core** - The core CamClient library, which provides an abstraction for the different protocols
* **camclient-dvr3** - The implementation of the DVR3 protocol

## Building
The project is built using Gradle. To build, run:

```
./gradlew build
```

## License
MIT

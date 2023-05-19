# Turismo Argentina 2
Tourism e-commerce made with Angular, Spring Boot (Java), Keycloak and PostgreSQL.  

This application allows you to:
- Create an account
- Modify your profile data
- Choose between three different languages (English, Spanish and Portuguese)
- See information about the provinces, locations and activities to do in Argentina
- Buy airline tickets to these locations
- Buy tickets for these activities
- Redeem discount coupons
- Manage your shopping cart
- Add provinces, locations, activities and coupons (as administrator user)
- And more

***Note:** this is a personal practice project and I do not allow its distribution.*  

## API Docs
To view the API documentation you must first [deploy](#deployment) the application and navigate to:
- http://localhost:8090/api-docs (To view the docs in JSON format)
- http://localhost:8090/swagger-ui.html (To view the docs with a graphical interface)
## Deployment
***Important note:** At the moment the app starts in developer mode and is not adapted for production.*  

The application can be deployed using a single command thanks to Docker.  
First of all, download [Docker Desktop](https://www.docker.com/products/docker-desktop/) if you don't have it installed locally. 

Then you have to clone this repository:  
`git clone https://github.com/MatiasGBT/TurismoArgentina2.git`  
or download it as a ZIP file.

Now, you have to add `127.0.0.1 keycloak` in your hosts file.  
On Mac and Linux, the hosts file is located in `/etc/hosts`, while on Windows it is located in `C:\Windows\System32\drivers\etc\hosts`.  
This is because you will access your application with a browser on your machine (which name is localhost, or 127.0.0.1), but inside Docker it will run in its own container, which name is keycloak. [JHipster project has the same problem](https://www.jhipster.tech/docker-compose/#7).

After that, you will be able to deploy this app running `docker compose up` in
the repository folder.  

You will need to have Docker Desktop open and running when you execute this command (and any other Docker command).  

The first time you run the application it will be slow because Docker needs to download and build the images specified in the `docker-compose.yml` file.  
You will be able to know when the project is ready when all the containers compile correctly. You will see the next lines in log of each container:
#### backend:  
`Started TurismoArgentinaBackendApplication in N seconds (process running for N)`
#### frontend:  
`✔ Compiled successfully.`
#### postgresql:  
`LOG:  database system is ready to accept connections`
#### keycloak:  
`Running the server in development mode. DO NOT use this configuration in production.`  

*Note: You can see the log of each container clicking on their names on Docker Desktop.*

*Note 2: To deploy the application you need the following ports to be free on your PC: 4200, 5432, 8080 and 8090.*

When all the containers are built, you can use the application by navigating to `http://localhost:4200`.

## Create an administrator user
After opening the app (see [Deployment](#deployment)) create a new user from the "Login" button and then "Register".  

After you have created your account, navigate to `http://localhost:8080/`, click on *Administration console* and log in with the credentials `admin` and `admin123`.

Select the TurismoArgentina realm:
![TurismoArgentina realm](https://i.imgur.com/CxumHWu.png)

Now, go to *Users* and click on the user you created earlier.  
Go to the *Role mapping* tab and click on *Assign role*.
![Role mapping tab](https://i.imgur.com/0VAl1i2.png)

Filter roles by client and search for the "admin" role. Select the one called `springbootbackend admin` and click the assign button.
![Filter by client](https://i.imgur.com/aqqZirx.png)
![Assign role](https://i.imgur.com/rycq0ZQ.png)

With this, the user you created will be able to access the administrator panel and perform the tasks that require that role.
## Screenshots
Some screenshots of the application.

![Index Page Desktop](https://i.imgur.com/nDuLBaf.png)
*Index page (desktop)*

![Index Page Mobile](https://i.imgur.com/4ECwjPX.png)
*Index page (mobile)*

![Provinces Page Desktop](https://i.imgur.com/YZHEAUm.png)
*Provinces page (desktop)*

![Provinces Page Mobile](https://i.imgur.com/FRNLixU.png)
*Provinces page (mobile)*

![Locations Page Desktop](https://i.imgur.com/0nNXrZ9.png)
*Locations page (desktop)*

![Locations Page Mobile](https://i.imgur.com/GQiZHSB.png)
*Locations page (mobile)*

![Activities Page Desktop](https://i.imgur.com/J2HVQ9q.png)
*Activities page (desktop)*

![Activities Page Mobile](https://i.imgur.com/c4ZHzLf.png)
*Activities page (mobile)*
## Tech Stack

**Client:** Angular

**Server:** Java, Spring Boot

**Authorization server:** Keycloak (v21.0.1)

**Database:** PostgreSQL
## External utilities
Utilities provided by other developers/designers used in this project:

- [keycloak-angular](https://www.npmjs.com/package/keycloak-angular)
- [ngx-translate](https://github.com/ngx-translate/core)
- [Font Awesome](https://fontawesome.com/)
- [Sweet Alert 2](https://sweetalert2.github.io/)
- [unDraw](https://undraw.co/illustrations)
## Author

[@MatiasGBT](https://github.com/MatiasGBT)
# FWA

Web application prototype using Java Servlet API stack. 
MVP application to partially implement registration and authentication mechanisms.
Each new exercise complements the previous one.

## ex00
Web application provide HTML registration and authentication pages in response to */signIn* and */signUp* URL requests. All data go to SignUp servlet in a POST request using \<form> HTML tag. The information is stored in a database, while the password shall be encrypted using **BCrypt** algorithm. When a POST request is sent to SignIn servlet with an email and a password, a check is performed if a corresponding user exists in the database, as well as their password is correct. If the check is successful, an HttpSession object with user attribute shall be generated (attribute’s value is an object containing current user data). In case of a failed authentication, user redirected back to the login page. Application’s Spring context  accessible to all servlets via **ServletContextListener**. Data for connecting to the database available in **application.properties**.

## ex01 
- Profile page access (the one with a single \<h1>Profile\</h1> tag) only to authenticated users.
- For security, we create a **Filter** that can handle any incoming requests. This filter will check for presence of the attribute in the current session. If the attribute is found, access to the requested resource (*/profile* in our case) shall be provided.
- Pages for */signUp* and */signIn* URLs may be retrieved for unauthorized requests. If the attribute is present, a user shall be redirected to */profile* page.

Also, in case of an unauthorized request of a page that requires an attribute, you shall return 403 (FORBIDDEN) status.

## ex02
Implemented profile page as a JSP file. The page display the following current user data:
  - First name
  - Last name
  - email

Information about the date / time / IP address of all user authentications as a list. The page have a user’s "avatar" loading functionality. The uploaded image shall be saved to disk. Since users can upload images in identical files.
  - An uploaded image available via its URL - http://host:port/app-name/images/imageunique-name
  - In application.properties, there storage.path parameter to indicate the path to the folder where uploaded files are stored.

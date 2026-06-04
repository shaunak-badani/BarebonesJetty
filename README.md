# FinanceBudge

> A simple bare-bones jetty application.

### Commands

In order to enable servlet on my Expense Submitter, I had to add the following:

```bash
java -jar $JETTY_HOME/start.jar jetty.base=. --add-modules=ee11-annotations,jsp
```
This is a one time addition, and does not need to be done for this project because it's been done once.

#### FinanceBudge

Compile Expense Submitter.

```bash
javac -cp $JETTY_HOME/lib/jetty-jakarta-servlet-api-5.0.2.jar webapps/FinanceBudge/WEB-INF/classes/expenses/ExpenseSubmitter.java
java -jar $JETTY_HOME/start.jar jetty.base=.
```

#### SocialFeed 

```bash
javac -cp $JETTY_HOME/lib/jetty-jakarta-servlet-api-5.0.2.jar webapps/SocialFeed/WEB-INF/classes/feed/FeedServlet.java webapps/SocialFeed/WEB-INF/classes/feed/data/Post.
java -jar $JETTY_HOME/start.jar jetty.base=.
```

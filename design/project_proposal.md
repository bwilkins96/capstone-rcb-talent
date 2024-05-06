<span style="text-decoration:underline;">Capstone Proposal</span>


_Problem Statement_

When interviewing for a job, it can be difficult to remember exactly where you are in the process, and even more of a nightmare to manage multiple interviews with different companies and positions. Job candidates have to remember things such as interview dates, type of interview, company-specific information, and what interviewing stage they are currently in, to name a few.
Some job seekers use Excel to keep track of their applications, but this approach is difficult to use consistently and scale. Downsides include manually typing into columns where many of them end up being empty, which can lead to inconsistency. There are also spelling mistakes and difficulty visualizing what stage of interviewing the candidate is in.


_Technical Solution_

Create an application for tracking information about individual applications.

**Scenario 1**
Dasy has been using [app name] for some time and wanted to check how many interviews she has had for a specific application. Navigating through the applications table, she selects the specific application to view the specific details and is able to see all of her interviews for that application
**Scenario 2**
Dani has just started his job search and would like a way to track all his applications after discovering that each company has their own workday portal. After discovering [app name], he begins sending out applications and logging them so he can keep track of all his applications without needing to create his own tracking system.


_Glossary_

**Application:** An individual application to a specific role.
**Company:** An organization with roles the user has applied to.
**Interview:** An individual interview for a specific role.
**Job Posting:** The job the user has applied to and related information.
**Status:** The current state of an application. I.e., ‘applied’, ‘interviewing’, ‘rejected’, ‘ghosted’, ’offer’.
**Skill:** Competencies required for a specific role.
**Result:** The outcome of an interview.
**Origin:** Where an application was submitted. I.e., ‘referral’, ‘cold apply’, ‘career fair’.


_High-Level Requirements_

* Create an application entry
* Edit an application entry
* Delete an application entry
* View applications overview
    * All applications
    * Applications filtered by company
* View detailed information about individual application
    * Can directly move to the update form from the application details page
    * Users are able to view all the interviews they have had or will have for this position
        * From this table of interviews, the user can add/edit/delete interviews
* View visualizations about applications (D3.js)
* Automatically change status to ‘ghosted’ after 6 months of ‘pending’?


_User Stories_

* As a user, I want to create an application entry, so that I can track my applications.
* As a user, I want to update an application entry, so that it’s up-to-date and useful.
* As a user, I want to be able to delete an application entry, in case I accidentally added an entry or otherwise want to remove the information.
* As a user, I want to view an overview of my applications, so that I can better understand the state of my job search.
* As a user, I want to see detailed information about a specific application, so that I can better understand the state of that application.
* As a user, I want to see visualized data about my applications, so that I can gain insights about my job search.
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import time
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.alert import Alert
import random


# accept the system alert
def alert_esc():
    submit = Alert(driver)
    time.sleep(1)
    print(submit.text)
    submit.accept()
    time.sleep(1)


def admin_alert_esc():
    # accept the backend alert
    submit = Alert(driver)
    time.sleep(1)
    submit.accept()
    time.sleep(1)


def sign_up(name,email,pwd):
    # to scrap the sign up link
    all_links = []
    for link in driver.find_elements_by_xpath("//*[@href]"):
        all_links.append(link.get_attribute('href'))
    for i in all_links:
        if "registration" in i:
            current_site=i
            driver.get(i)
    # details of sign up of VirtualUser
    driver.find_element_by_id("userName").send_keys(name)
    driver.find_element_by_id("email").send_keys(email)
    driver.find_element_by_id("password").send_keys(pwd)
    driver.find_element_by_id("repassword").send_keys(pwd)
    driver.find_element_by_id("year2").click()
    # use the java script to select
    js = "var q=document.getElementById(\"brand\");q.checked=true;"
    driver.execute_script(js)
    driver.find_element_by_id("enrol").click()
    print("Begin to select your course")
    WebDriverWait(driver, 1000)
    # use java script to select course
    js = """
        var qq = document.getElementsByName("course");
        for(var a =0; a < qq.length - 4; a++){
            qq[a].checked = true;
        }  
    """
    driver.execute_script(js)
    js = "var q=document.getElementById(\"registration-submit\");q.click();"
    driver.execute_script(js)


def virtual1_sign_up():
    # to scrap the sign up link
    all_links = []
    for link in driver.find_elements_by_xpath("//*[@href]"):
        all_links.append(link.get_attribute('href'))
    for i in all_links:
        if "registration" in i:
            current_site=i
            driver.get(i)
    # details of sign up of VirtualUser
    driver.find_element_by_id("userName").send_keys('VirtualUser')
    driver.find_element_by_id("email").send_keys('virtual@virtual.com')
    driver.find_element_by_id("password").send_keys('ShBr123')
    driver.find_element_by_id("repassword").send_keys('ShBr123')
    driver.find_element_by_id("year2").click()
    # use the java script to select
    js = "var q=document.getElementById(\"brand\");q.checked=true;"
    driver.execute_script(js)
    driver.find_element_by_id("enrol").click()
    print("Begin to select your course")
    WebDriverWait(driver, 1000)
    # use java script to select course
    js = """
        var qq = document.getElementsByName("course");
        for(var a =0; a < qq.length - 4; a++){
            qq[a].checked = true;
        }  
    """
    driver.execute_script(js)
    js = "var q=document.getElementById(\"registration-submit\");q.click();"
    driver.execute_script(js)


def virtual1_login():
    #  login into the student system with the created
    driver.find_element_by_name("username").send_keys('virtual@virtual.com')
    driver.find_element_by_name("password").send_keys('ShBr123')
    driver.find_element_by_class_name("submitbox").click()


def login2():
    # virtual user2 login
    driver.find_element_by_name("username").send_keys('virtual2@virtual.com')
    # type the password
    driver.find_element_by_name("password").send_keys('ShBr123')
    # click login
    driver.find_element_by_class_name("submitbox").click()


def login(pwd,email):
    #  login into the student system with the created
    time.sleep(5)
    driver.find_element_by_name("username").send_keys(email)
    driver.find_element_by_name("password").send_keys(pwd)
    driver.find_element_by_class_name("submitbox").click()


def problem_set():
    time.sleep(3)
    all_links = []
    # scrap all the href link in the page
    for link in driver.find_elements_by_xpath("//*[@href]"):
        all_links.append(link.get_attribute('href'))
    # find exercises link
    for i in all_links:
        if "exercises" in i:
            driver.get(i)
    driver.find_element_by_id("show_all").click()
    time.sleep(2)
    # select java
    driver.find_element_by_partial_link_text("Java").click()
    time.sleep(1)
    # select two sums in java and add to favourite lists
    driver.find_element_by_link_text("Two Sums").click()
    time.sleep(1)
    # click the solution button
    driver.find_element_by_xpath('''//*[@id="main_content"]/div/div/div/div[1]/ul/div/li/button''').click()
    time.sleep(1)
    driver.find_element_by_xpath('''//*[@id="main_content"]/div/div/div/div[1]/ul/li[2]/a/i''').click()


def user_manager_myCourses():
    time.sleep(3)
    # click into user manager
    driver.find_element_by_xpath('''/html/body/div[1]/div/ul/li[4]/a''').click()
    time.sleep(2)
    # drop the course
    driver.find_element_by_xpath('''//*[@id="cour2"]''').click()
    time.sleep(2)
    driver.find_element_by_xpath('''//*[@id="allcourses"]/li[2]/h2/a[1]''').click()


def user_manager_exercises():
    time.sleep(3)
    driver.find_element_by_xpath('''/html/body/div[1]/div/ul/li[4]/a''').click()
    time.sleep(2)
    driver.find_element_by_xpath('''//*[@id="cour3"]''').click()
    time.sleep(2)
    
    driver.find_element_by_xpath("/html/body/div[3]/div/div[2]/div[2]/div[2]/ul/li/h2/a[2]").click()
    print("my exercises checked")

def log_out():
    #  log out the system
    time.sleep(4)
    driver.find_element_by_xpath('''/html/body/div[1]/div/ul/li[6]/a''').click()
    print("successfully log out")


def admin_login():
    # admin login
    driver.get("https://10.86.164.216/alogin")
    driver.find_element_by_name("username").send_keys('admin@aecl.com')
    driver.find_element_by_name("password").send_keys('Furrow-notion-siberia-Seldom-puppy')
    driver.find_element_by_class_name("submitbox").click()
    time.sleep(4)


def admin_logout():
    # admin log out
    driver.find_element_by_xpath('''/html/body/div[1]/div/ul/li/a''').click()


def admin_delete_user():
    driver.find_element_by_xpath('''//*[@id="user_content"]/tr[8]/td[1]/button''').click()



def user_post():
    # wait until the browser loads everything
    wait = WebDriverWait(driver, 10).until(
        EC.presence_of_element_located((By.ID, "cour1"))
    )
    driver.find_element_by_id("cour1").click()

    time.sleep(2)
    # wait until the browser loads everything
    wait = WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.ID, "post_action"))
        )
    driver.find_element_by_id("post_action").click()
    time.sleep(2)
    # type the title of your psot
    driver.find_element_by_name("postTitle").send_keys('post for test only')
    time.sleep(2)
    driver.find_element_by_xpath("/html/body/div[3]/div/div[2]/div[1]/div/div/div/div/div/form/div[2]/div/div/div/input").click()
    driver.find_element_by_xpath("/html/body/div[3]/div/div[2]/div[1]/div/div/div/div/div/form/div[2]/div/div/dl/dd[2]").click()
    # send the message you want to post
    driver.find_element_by_name("postContent").send_keys('As title')
    # submit the post
    driver.find_element_by_id("submit_post").click()


def user_changepwd():
    # wait until the browser loads everything
    wait = WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.ID, "cour1"))
        )
    driver.find_element_by_id("cour1").click()
    # scrap all the herf link on the page
    all_links=[]
    for link in driver.find_elements_by_xpath("//*[@href]"):
        all_links.append(link.get_attribute('href'))
    # find the personal page
    for i in all_links:
        if "personal" in i:
            driver.get(i)
    # click the personal page button
    driver.find_element_by_xpath("/html/body/div[3]/div/div[1]/div/div/ul/li[1]/div/a").click()
    # change the password by given keys
    driver.find_element_by_name("pwd").send_keys("ShBr123")
    driver.find_element_by_name("newPwd").send_keys("BrSh123")
    driver.find_element_by_name("retype").send_keys("BrSh123")
    driver.find_element_by_xpath("/html/body/div[3]/div/div[2]/div[1]/div/div/div/div/form/div[4]/div/button[1]").click()


def random_user_changepwd(pwd, new_pwd):
    # wait the page to load all the things
    wait = WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.ID, "cour1"))
        )
    driver.find_element_by_id("cour1").click()
    # find all href links in this page
    all_links=[]
    for link in driver.find_elements_by_xpath("//*[@href]"):
        all_links.append(link.get_attribute('href'))
    # get the personal page
    for i in all_links:
        if "personal" in i:
            driver.get(i)
    time.sleep(2)
    driver.find_element_by_xpath("/html/body/div[3]/div/div[1]/div/div/ul/li[1]/div/a").click()
    time.sleep(2)
    driver.find_element_by_name("pwd").send_keys(pwd)
    time.sleep(2)
    driver.find_element_by_name("newPwd").send_keys(new_pwd)
    time.sleep(2)
    driver.find_element_by_name("retype").send_keys(new_pwd)
    # click to confirm
    driver.find_element_by_xpath("/html/body/div[3]/div/div[2]/div[1]/div/div/div/div/form/div[4]/div/button[1]").click()


def user_messages():
    # wait until the browser loads everything
    wait = WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.ID, "cour1"))
        )
    driver.find_element_by_id("cour1").click()
    # find the message page
    all_links=[]
    for link in driver.find_elements_by_xpath("//*[@href]"):
        all_links.append(link.get_attribute('href'))
    for i in all_links:
        if "mess" in i:
            driver.get(i)
    time.sleep(2)
    # click the page buttton
    driver.find_element_by_xpath("/html/body/div[3]/div/div[1]/div/div[1]/a").click()
    # type the message title
    time.sleep(2)
    driver.find_element_by_name("title").send_keys('messenger for test only')
    # select the people you want to send the message
    time.sleep(2)
    driver.find_element_by_xpath("/html/body/div[3]/div/div[2]/div[1]/div/div/div/div/div/form/div[2]/div/div/div/input").click()
    time.sleep(2)
    driver.find_element_by_xpath("/html/body/div[3]/div/div[2]/div[1]/div/div/div/div/div/form/div[2]/div/div/dl/dd[9]").click()
    time.sleep(2)
    driver.find_element_by_name("content").send_keys('As title')
    # submit the message
    driver.find_element_by_id("submit_message").click()


def user_comment():
    # wait until the browser loads everything
    wait = WebDriverWait(driver, 10).until(
        EC.presence_of_element_located((By.ID, "cour1"))
    )
    time.sleep(2)
    driver.find_element_by_xpath("/html/body/div[3]/div/div[1]/div/div[2]/ul/li[1]/div/a").click()
    time.sleep(2)
    # finds the comment page
    driver.find_element_by_xpath("/html/body/div[3]/div/div[2]/div[2]/div/ul/li[1]/h2/a").click()
    time.sleep(2)
    # sends a comment
    driver.find_element_by_xpath("/html/body/div[3]/div/div[1]/div[2]/div/form/div[1]/div/div/label/textarea").send_keys("I'm a virtual user.")
    # find the submit button and click on it
    driver.find_element_by_xpath("/html/body/div[3]/div/div[1]/div[2]/div/form/div[2]/button").click()


def swager():
    # find the href of our api
    all_links = []
    # scrap all the href links
    for link in driver.find_elements_by_xpath("//*[@href]"):
        all_links.append(link.get_attribute('href'))
    # get the link of api
    for i in all_links:
        if "swa" in i:
            driver.get(i)


def virtual_user2_post_addexercises_and_check():
    url = 'https://10.86.164.216/'
    driver.get(url)
    time.sleep(3)
    # login and post a message
    login2()
    user_post()
    alert_esc()
    #  go to problem set to add exercises
    problem_set()
    alert_esc()
    # go to user manager to check whether add successfully
    user_manager_exercises()
    log_out()


def virtual_user_2_comment():
    url = 'https://10.86.164.216/'
    driver.get(url)
    time.sleep(3)
    # virtual user2 login
    login2()
    user_comment()
    # accept the system alert
    alert_esc()
    log_out()


def random_user_signUP_dropCourses_changePasswrd():
    url = 'https://10.86.164.216/'
    driver.get(url)
    time.sleep(3)
    # seed
    a = "1234567890"
    b = "abcdefghijklmnopqrstuvwxyz"
    c = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    pwd = ""
    for i in range(4):
        pwd += random.choice(a)
        pwd += random.choice(b)
        pwd += random.choice(c)
    # generate virtual user's name
    name = "VirtualUser"
    for i in range(3):
        name += random.choice(a)
        name += random.choice(b)
        name += random.choice(c)
    # generate virtual user's email
    email = ""
    for i in range(6):
        email += random.choice(a)
        email += random.choice(b)
        email += random.choice(c)
    email += "@"
    for i in range(3):
        email += random.choice(a)
        email += random.choice(b)
        email += random.choice(c)
    email += ".com"
    # random virtual user sign up and drop course
    sign_up(name, email, pwd)
    login(pwd, email)
    user_manager_myCourses()
    alert_esc()
    # GENERATE NEW PASSWORD
    new_pwd = ""
    for i in range(4):
        new_pwd += random.choice(a)
        new_pwd += random.choice(b)
        new_pwd += random.choice(c)
    # change the password
    random_user_changepwd(pwd, new_pwd)
    alert_esc()


def virtual_user2_message():
    # user2 login
    url = 'https://10.86.164.216/'
    driver.get(url)
    time.sleep(3)
    login2()
    # virtual2 send the message
    user_messages()
    alert_esc()
    log_out()


def virtual_user2_swagger():
    # user2 login
    url = 'https://10.86.164.216/'
    driver.get(url)
    time.sleep(3)
    login2()
    # go to the page of api
    swager()
    log_out()


def admin_login_delete():
    url = 'https://10.86.164.216/'
    driver.get(url)
    time.sleep(3)
    admin_login()
    admin_delete_user()
    admin_alert_esc()
    alert_esc()
    admin_logout()


if __name__ == '__main__':
    # set the chrome options to headless
    chrome_options = Options()
    chrome_options.add_argument('--headless')
    chrome_options.add_argument('--disable-gpu')
    chrome_options.add_argument('--ignore-certificate-errors')
    # give the path of our web drivers
    path = 'chromedriver.exe'
    # set up the selenium
    driver = webdriver.Chrome(executable_path=path, options=chrome_options)
    # call the virtual user2 to do post, add exercises, check whether add
    virtual_user2_post_addexercises_and_check()
    time.sleep(2)
    # let the virtual user2 to comment
    virtual_user_2_comment()
    time.sleep(2)
    # generate a random user to sign up, drop courses then change password
    random_user_signUP_dropCourses_changePasswrd()
    time.sleep(2)
    # let the virtual user2 send the message
    virtual_user2_message()
    time.sleep(2)
    # let the virtual user2 go to the api page
    virtual_user2_swagger()
    time.sleep(2)
    # go to the admin page and delete the random user
    admin_login_delete()



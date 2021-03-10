'''
    This file will handle our typical Bottle requests and responses 
    You should not have anything beyond basic page loads, handling forms and 
    maybe some simple program logic
'''

from bottle import route, get, post, request, static_file, response, redirect


#-----------------------------------------------------------------------------
# Static file paths
#-----------------------------------------------------------------------------

# Allow image loading
@route('/img/<picture:path>')
def serve_pictures(picture):
    '''
        serve_pictures

        Serves images from static/img/

        :: picture :: A path to the requested picture

        Returns a static file object containing the requested picture
    '''
    return static_file(picture, root='static/img/')


# Allow image loading
@route('/html/<html:path>')
def serve_pages(html_page):
    '''
        serve_html

        Serves html from static/html/

        Returns a static file object containing the requested picture
    '''
    return static_file(html_page, root='static/html/')

#-----------------------------------------------------------------------------

# Allow CSS
@route('/css/<css:path>')
def serve_css(css):
    '''
        serve_css

        Serves css from static/css/

        :: css :: A path to the requested css

        Returns a static file object containing the requested css
    '''
    return static_file(css, root='static/css/')

#-----------------------------------------------------------------------------

# Allow javascript
@route('/js/<js:path>')
def serve_js(js):
    '''
        serve_js

        Serves js from static/js/

        :: js :: A path to the requested javascript

        Returns a static file object containing the requested javascript
    '''
    return static_file(js, root='static/js/')

#-----------------------------------------------------------------------------
# Pages
#-----------------------------------------------------------------------------

def getLoginId():
    token = request.get_cookie('token')
    return token

# Redirect to login


#-----------------------------------------------------------------------------

# Display the login page
@get('/')
@get('/login')
def get_login_controller():
    '''
        get_login
        Serves the login page
    '''
    return serve_pages("login.html")

#-----------------------------------------------------------------------------
# display admin login page
@get('/alogin')
def get_admin_login():
    return serve_pages("admin_login.html")

# display discussion board page
@get('/discussion')
def get_discussion():
    return serve_pages("discussion.html")


#-----------------------------------------------------------------------------
# display admin management page
@get('/admin')
def get_admin():
    admin_cookie = request.get_cookie('atoken')
    if admin_cookie:
        return serve_pages("admin.html")
    else:
        redirect('/alogin')

# Display the login page
@get('/')
@get('/login')
def get_login_controller():
    '''
        get_login
        Serves the login page
    '''
    return serve_pages("login.html")

#-----------------------------------------------------------------------------


# Display the registration page
@get('/registration')
def get_registration_controller():
    '''
        get_login
        Serves the login page
    '''
    return serve_pages("registration.html")

# display messaging service page
@get('/messages')
def get_messages():
    pid = getLoginId()
    if pid is not None:
        return serve_pages("messages.html")
    else:
        return redirect('/login')
   

#-----------------------------------------------------------------------------
# display personal management page
@get('/personal')
def get_personal_page():
    pid = getLoginId()
    if pid is not None:
        return serve_pages("user_details.html")
    else:
        redirect('/login')
   

#-----------------------------------------------------------------------------
# display logout management page
@get('/logout')
def logout():
    response.delete_cookie("token")
    response.delete_cookie("atoken")
    return serve_pages("logout.html")

# display admin logout management page
@get('/alogout')
def adminlogout():
    #TODO
    return serve_pages("")


# display exercise page
@get('/exercises')
def get_exercises():
    pid = getLoginId()
    if pid is not None:
        return serve_pages("problem.html")
    else:
        redirect('/login')
   

# display problem details page
@get('/pdetails/<id>')
def get_resources_download(id):
    pid = getLoginId()
    if pid is not None:
        return serve_pages("problem_details.html")
    else:
        redirect('/login')
# display post details page
@get('/postdetails/<id>')
def get_post_details(id):
    pid = getLoginId()
    if pid is not None:
        return serve_pages("post_details.html")
    else:
        redirect('/login')

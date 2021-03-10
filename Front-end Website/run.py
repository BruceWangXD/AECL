import bottle
import sys
from bottle import run
import controller

host = 'localhost'
port = 2333
debug = True

def run_server():    
    '''
        run_server
        Runs a bottle server
    '''
    run(host=host, port=port, debug=debug)

if __name__ == "__main__":
    run_server()

app = bottle.default_app()

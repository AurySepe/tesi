import os
import sys

from Inject.Singletons import Listner

if __name__ == '__main__':
    try:
        Listner.listen()
    except KeyboardInterrupt:
        print('Interrupted')
        try:
            sys.exit(0)
        except SystemExit:
            os._exit(0)

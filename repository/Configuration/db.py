import os.path

from sqlalchemy import create_engine
from sqlalchemy.orm import declarative_base

from configuration import ROOT_DIR


engine = create_engine(f"sqlite+pysqlite:///{os.path.join(ROOT_DIR,'DB/database2.db')}", echo=True, future=True)
Base = declarative_base()



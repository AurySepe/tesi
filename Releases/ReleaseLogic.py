import requests
from bs4 import BeautifulSoup
import re


def findLastPage(author,repository):
    try:
        page = requests.get(f"https://github.com/{author}/{repository}/releases?page=1").text
        soup = BeautifulSoup(page,features="html.parser")
        pages = soup.find_all("a",attrs={"aria-label" : re.compile(r"^Page")})
        lastPage = pages[-1]
        return int(lastPage.text)
    except:
        return 0


def getCommitsFromPage(author,repository,pageNum = None):
    if pageNum != None:
        page = requests.get(f"https://github.com/{author}/{repository}/releases?page={pageNum}").text
    else:
        page = requests.get(f"https://github.com/{author}/{repository}/releases").text
    soup = BeautifulSoup(page,features="html.parser")
    l = soup.find_all("a", attrs={"data-hovercard-type": "commit"})
    result = []
    for a in l:
        if a.findChildren("svg"):
            url = a["data-hovercard-url"]
            commit = url.split("commit/")[1].split("/hovercard")[0]
            result.append(commit)
    return result


def authorAndRepoFromOrigin(origin):
    author = origin.split("https://github.com/")[1].split("/")[0]
    repository = origin.split("https://github.com/")[1].split("/")[1].split(".git")[0]
    return author,repository


def getReleases(origin):
    author,repository = authorAndRepoFromOrigin(origin)
    numOfPages = findLastPage(author,repository)
    result = []
    if numOfPages == 0:
        try:
            result += getCommitsFromPage(author,repository)
        except Exception as e:
            pass
    for i in range(1,numOfPages + 1):
        result += getCommitsFromPage(author,repository,i)
    return result


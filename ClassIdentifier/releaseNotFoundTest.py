from pydriller import Repository

from releaseHistoryLogic import getClassesHistory


def traverse_commits(self):
    """
    Analyze all the specified commits (all of them by default), returning
    a generator of commits.
    """
    for path_repo in self._conf.get('path_to_repos'):
        with self._prep_repo(path_repo=path_repo) as git:

            # Get the commits that modified the filepath. In this case, we can not use
            # git rev-list since it doesn't have the option --follow, necessary to follow
            # the renames. Hence, we manually call git log instead
            if self._conf.get('filepath') is not None:
                self._conf.set_value(
                    'filepath_commits',
                    git.get_commits_modified_file(self._conf.get('filepath'),
                                                  self._conf.get('include_deleted_files'))
                )

            # Gets only the commits that are tagged
            if self._conf.get('only_releases'):
                self._conf.set_value('tagged_commits', git.get_tagged_commits())

            # Build the arguments to pass to git rev-list.
            rev, kwargs = self._conf.build_args()

            for commit in git.get_list_commits(rev, **kwargs):
                yield commit




Repository.traverse_commits = traverse_commits

PathToRepo = "C:/Users/aurel/Desktop/Lavoro tesi/Ant/ant"
OutputDir = "C:/Users/aurel/Desktop/Lavoro tesi/Ant/history"
releases = ["5a008aafac7cc571bd43d3d911964d77168cf381","a525602c46801329510c2f480ebdc292f04d2467","068f7348847e10c8deca027f67e7142c559cdfa0","e25567d31849baafa30f21ac10cfed5eb958e245","395544ee5053921a9332828a9dd23a6fa32f9760","e4ed29508743d2911725ef07dc84920567c4d2a1","83213625e56a8ca6f149156740ff5c48bcb83525"]
releasesOutputFile = "C:/Users/aurel/Desktop/Lavoro tesi/Ant/releases.txt"

getClassesHistory(PathToRepo,OutputDir,releases,releasesOutputFile)



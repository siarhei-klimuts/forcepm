# forcepm

## Package builder
Should be able to generate package from related commits

### Steps
1. svn log --limit 100 --xml
2. Filter commits by #p marker
3. Create package.xml
4. Create destructiveChanges.xml
5. Create tests.txt
6. Generate package.zip

### Features
* Resolve dependencies
* Update packages
* Merge packages
* Test all possible dependencies and conflicts

## Dependency resolving
* Exists not installed package with file revision < current package file revision
* Build dependency tree on every commit and package generation

## Package storage
All generated packages should be placed to a storage. 
Storage should be able to return package and package info by package name, id or file revision.
Package info should contain:
* Name
* Id
* First revision #
* File to revision map
* Dependencies list
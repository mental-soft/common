[![Build Status](http://ci.teammental.com/buildStatus/icon?job=common-multipipeline/master)](http://ci.teammental.com/job/common-multipipeline/job/master/)

# Mental Team - Common

## Gradle

**Build**

<code>
gradlew build
</code>

**Run**

<code>
gradlew bootRun
</code>

**Dependancy**
* Postgresql
* common-dev, common-qa
* dbuser:12345


## Docker ([mental/common](https://hub.docker.com/r/mental/common/))

**Build**

<code>
docker build -t mental/common .
</code>

**Run**
<code>
docker run -it --name mencommon -e connection <postgresql_container_name> mental/common
</code>

**Run (docker-compose)**

<code>
docker compose up
</code>

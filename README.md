[![Build Status](https://travis-ci.org/mental-soft/common.svg?branch=master)](https://travis-ci.org/mental-soft/common)

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
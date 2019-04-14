#! /bin/sh

TMS=$(date +"%Y%m%d%H%M%S")
WHOAMI=$(whoami)
PROFILE="/home/"${WHOAMI}"/.bashrc"

echo "$TMS | Begin"

### -----------------------------------
DIR=${HOME}"/opt"
if [ -d ${DIR} ]; then
	echo "Backing up ${DIR} to ${DIR}_${TMS}"
	mv ${DIR} ${DIR}_${TMS}
fi

echo "Creating ${DIR} for installation of tools and software."
mkdir ${DIR}
cd ${DIR}

### -----------------------------------
echo "JAVA | Downloading"
curl -O -L -b "oraclelicense=a" https://download.oracle.com/otn-pub/java/jdk/12+33/312335d836a34c7c8bba9d963e26dc23/jdk-12_linux-x64_bin.tar.gz

RET=$?
if [ ${RET} -ne 0 ]; then
	echo "Java download failed. Exiting"
	exit ${RET}
fi

### -----------------------------------
echo "JAVA | unzip and untar"
tar -xzf jdk-12_linux-x64_bin.tar.gz

RET=$?
if [ ${RET} -ne 0 ]; then
	echo "Java unzip and untar failed. Exiting"
	exit ${RET}
fi

### -----------------------------------
echo "JAVA | updating .bashrc profile"
echo "export JAVA_HOME=${DIR}/jdk-12" >> ${PROFILE}
echo 'export PATH=${JAVA_HOME}/bin:${PATH}' >> ${PROFILE}

### -----------------------------------
echo "Sourcing : ${PROFILE}"
cd ${HOME}
. ./.bashrc
cd - > /dev/null

echo "Verifying Java version"
java -version

### -----------------------------------
echo "Downloading Eclipse"
curl -O -L http://ftp.fau.de/eclipse/technology/epp/downloads/release/photon/R/eclipse-jee-photon-R-linux-gtk-x86_64.tar.gz

RET=$?
if [ ${RET} -ne 0 ]; then
        echo "Eclipse download failed. Exiting"
        exit ${RET}
fi

### -----------------------------------
echo "Eclipse | unzip and untar"
tar -xzf eclipse-jee-photon-R-linux-gtk-x86_64.tar.gz

RET=$?
if [ ${RET} -ne 0 ]; then
        echo "Eclipse unzip and untar failed. Exiting"
        exit ${RET}
fi

### -----------------------------------
echo "Eclipse | updating .bashrc profile"
echo "export ECLIPSE_HOME=${DIR}/eclipse" >> ${PROFILE}
echo 'export PATH=${ECLIPSE_HOME}:${PATH}' >> ${PROFILE}


### -----------------------------------
TMS=$(date +"%Y%m%d%H%M%S")
echo "$TMS | End"

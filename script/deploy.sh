rm -rf low-level-platform
ls -l
git clone  https://github.com/chenpei1993/low-level-platform.git
cd low-level-platform
git checkout dev
mvn verify
docker-compose -f docker-compose-test.yml down
docker-compose -f docker-compose-test.yml build --no-cache
docker-compose -f docker-compose-test.yml up -d

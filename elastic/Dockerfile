FROM elasticsearch:7.17.10


ADD ./script/wait-for-it.sh /utils/wait-for-it.sh

COPY ./script/insert.sh /script/insert.sh

RUN chmod 777 /script/insert.sh

USER elasticsearch

RUN elasticsearch -p /tmp/epid -E discovery.type=single-node \
    & /bin/bash /utils/wait-for-it.sh -t 0 localhost:9200 -- /script/insert.sh; \
    kill $(cat /tmp/epid) && wait $(cat /tmp/epid); exit 0;

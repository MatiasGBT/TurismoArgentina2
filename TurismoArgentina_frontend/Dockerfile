FROM node:16

RUN mkdir -p /TurismoArgentina/frontend

WORKDIR /TurismoArgentina/frontend

COPY package.json ./

RUN npm install

COPY . .

EXPOSE 4200

CMD ["npm", "start"]
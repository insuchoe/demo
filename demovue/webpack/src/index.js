import _ from 'lodash';
function component(){
    let ele = document.createElement('div');

    // lodash is required for the next line to work
    ele.innerHTML= _.join(['Hello','webpack'],' ');
    return ele;
}
document.body.appendChild(component());
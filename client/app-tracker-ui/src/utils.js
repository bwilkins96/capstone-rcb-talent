export function getOption(selectEle) {
    const options = selectEle.options;
    const idx = selectEle.selectedIndex;
    
    return options[idx].value;
}